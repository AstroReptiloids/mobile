package com.example.myapplication.di.modules

import android.app.Application
import android.content.Context
import com.example.myapplication.BuildConfig
import com.example.myapplication.Constants
import com.example.myapplication.data.network.AuthorizationInterceptor
import com.example.myapplication.data.network.NetworkStateWatcher
import com.example.myapplication.data.network.ServerResponseHandler
import com.example.myapplication.data.network.service.INetworkService
import com.example.myapplication.data.network.service.NetworkService
import com.example.myapplication.data.network.service.RestApi
import com.example.myapplication.data.network.service.WebSocketApi
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.tinder.scarlet.Scarlet
import com.tinder.scarlet.lifecycle.android.AndroidLifecycle
import com.tinder.scarlet.messageadapter.gson.GsonMessageAdapter
import com.tinder.scarlet.streamadapter.rxjava2.RxJava2StreamAdapterFactory
import com.tinder.scarlet.websocket.okhttp.newWebSocketFactory
import dagger.Module
import dagger.Provides
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module
class NetworkModule(private val application: Application) {

    @Provides
    @Singleton
    internal fun provideRestApi(retrofit: Retrofit): RestApi {
        return retrofit.create(RestApi::class.java)
    }

    @Provides
    @Singleton
    internal fun provideRetrofit(
        converterFactory: Converter.Factory,
        httpClient: OkHttpClient
    ): Retrofit {
        return Retrofit.Builder()
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
            .addConverterFactory(converterFactory)
            .client(httpClient).baseUrl(Constants.SERVER_MAIN_URL).build()
    }

    @Provides
    @Singleton
    internal fun provideWebSocketApi(scarlet: Scarlet): WebSocketApi {
        return scarlet.create(WebSocketApi::class.java)
    }

    @Provides
    @Singleton
    internal fun provideScarlet(httpClient: OkHttpClient): Scarlet {
        return Scarlet.Builder()
            .webSocketFactory(httpClient.newWebSocketFactory(Constants.SERVER_MAIN_URL))
            .addMessageAdapterFactory(GsonMessageAdapter.Factory())
            .addStreamAdapterFactory(RxJava2StreamAdapterFactory())
            .lifecycle(
                AndroidLifecycle.ofApplicationForeground(application)
            )
            .build()
    }

    @Provides
    @Singleton
    internal fun provideHttpClient(authorizationInterceptor: AuthorizationInterceptor): OkHttpClient {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor.Level.BODY
        } else {
            HttpLoggingInterceptor.Level.NONE
        }
        return OkHttpClient.Builder()
            .connectTimeout(15, TimeUnit.SECONDS)
            .readTimeout(15, TimeUnit.SECONDS)
            .addInterceptor(interceptor)
            .addInterceptor(authorizationInterceptor)
            .build()
    }

    @Provides
    @Singleton
    internal fun provideGson(): Gson {
        return GsonBuilder()
            .setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)
            .setPrettyPrinting()
            .serializeNulls()
            .setLenient()
            .create()
    }

    @Provides
    @Singleton
    internal fun provideConverterFactory(gson: Gson): Converter.Factory {
        return GsonConverterFactory.create(gson)
    }

    @Provides
    @Singleton
    internal fun providerResponseHandler(): ServerResponseHandler {
        return ServerResponseHandler()
    }

    @Provides
    @Singleton
    internal fun provideNetworkService(
        serverApi: RestApi,
        webSocketApi: WebSocketApi,
        networkStateWatcher: NetworkStateWatcher,
        serverResponseHandler: ServerResponseHandler
    ): INetworkService {
        return NetworkService(serverApi, webSocketApi, networkStateWatcher, serverResponseHandler)
    }

    @Provides
    @Singleton
    internal fun provideNetworkStateWatcher(context: Context): NetworkStateWatcher {
        return NetworkStateWatcher(context)
    }

    @Provides
    @Singleton
    internal fun provideAuthorizationInterceptor(): AuthorizationInterceptor {
        return AuthorizationInterceptor()
    }
}