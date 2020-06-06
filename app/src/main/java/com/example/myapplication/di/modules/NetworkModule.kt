package com.example.myapplication.di.modules

import android.content.Context
import com.example.myapplication.BuildConfig
import com.example.myapplication.Constants
import com.example.myapplication.data.network.NetworkStateWatcher
import com.example.myapplication.data.network.ServerResponseHandler
import com.example.myapplication.data.network.service.INetworkService
import com.example.myapplication.data.network.service.NetworkService
import com.example.myapplication.data.network.service.ServerApi
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.example.myapplication.di.scopes.PerActivity
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

@Module
class NetworkModule {

    @Provides
    @PerActivity
    internal fun providerServerUrl(): String {
        return Constants.SERVER_MAIN_URL
    }

    @Provides
    @PerActivity
    internal fun provideServerApi(retrofit: Retrofit): ServerApi {
        return retrofit.create(ServerApi::class.java)
    }

    @Provides
    @PerActivity
    internal fun provideRetrofit(builder: Retrofit.Builder, serverUrl: String): Retrofit {
        return builder.baseUrl(serverUrl).build()
    }

    @Provides
    @PerActivity
    internal fun provideRetrofitBuilder(converterFactory: Converter.Factory): Retrofit.Builder {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor.Level.BODY
        } else {
            HttpLoggingInterceptor.Level.NONE
        }
        val client = OkHttpClient.Builder()
            .connectTimeout(15, TimeUnit.SECONDS)
            .readTimeout(15, TimeUnit.SECONDS)
            .addInterceptor(interceptor)
            .build()


        return Retrofit.Builder()
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
            .addConverterFactory(converterFactory)
            .client(client)
    }

    @Provides
    @PerActivity
    internal fun provideGson(): Gson {
        return GsonBuilder()
            .setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)
            .setPrettyPrinting()
            .serializeNulls()
            .setLenient()
            .create()
    }

    @Provides
    @PerActivity
    internal fun provideConverterFactory(gson: Gson): Converter.Factory {
        return GsonConverterFactory.create(gson)
    }

    @Provides
    @PerActivity
    internal fun providerResponseHandler(): ServerResponseHandler {
        return ServerResponseHandler()
    }

    @Provides
    @PerActivity
    internal fun provideNetworkService(
        serverApi: ServerApi,
        networkStateWatcher: NetworkStateWatcher,
        serverResponseHandler: ServerResponseHandler
    ): INetworkService {
        return NetworkService(serverApi, networkStateWatcher, serverResponseHandler)
    }

    @Provides
    @PerActivity
    internal fun provideNetworkStateWatcher(context: Context): NetworkStateWatcher {
        return NetworkStateWatcher(context)
    }
}