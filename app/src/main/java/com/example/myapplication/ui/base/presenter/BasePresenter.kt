package com.example.myapplication.ui.base.presenter

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import com.example.myapplication.ui.base.view.IView
import com.example.myapplication.ui.base.handler.IErrorHandler
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

abstract class BasePresenter<V : IView> : LifecycleObserver, IPresenter<V> {

    private val compositeDisposable = CompositeDisposable()
    protected var errorHandler: IErrorHandler? = null
    protected var view: V? = null

    override fun attachLifecycle(lifecycle: Lifecycle) {
        lifecycle.addObserver(this)
    }

    override fun detachLifecycle(lifecycle: Lifecycle) {
        lifecycle.removeObserver(this)
    }

    override fun bindView(view: V) {
        this.view = view
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    open fun onPresenterDestroy() {
        view = null
        compositeDisposable.clear()
    }

    protected open fun showError(throwable: Throwable) {
        try {
            errorHandler?.handleError(throwable, view)
        } catch (ignored: Throwable) {
            //Ignored
        }
    }

    protected fun hideProgress() {
        view?.hideProgress()
    }

    protected fun showProgress() {
        view?.showProgress()
    }

    protected fun <T> runAsync(
        single: Single<T>,
        result: (result: T) -> Unit,
        error: (throwable: Throwable) -> Unit = { showError(it) },
        showProgress: Boolean = false
    ) {
        addDisposable(
            if (showProgress) {
                schedulingWithProgress(single)
            } else {
                scheduling(single)
            }
                .subscribe(
                    { result.invoke(it) },
                    { error.invoke(it) }
                )
        )
    }

    protected fun <T> runAsync(
        completable: Completable,
        result: () -> Unit,
        error: (throwable: Throwable) -> Unit = { showError(it) },
        showProgress: Boolean = false
    ) {
        addDisposable(
            if (showProgress) {
                schedulingWithProgress(completable)
            } else {
                scheduling(completable)
            }
                .subscribe(
                    { result.invoke() },
                    { error.invoke(it) }
                )
        )
    }

    protected fun <T> runAsync(
        observable: Observable<T>,
        result: (result: T) -> Unit,
        error: (throwable: Throwable) -> Unit = { showError(it) },
        showProgress: Boolean = false
    ) {
        addDisposable(
            if (showProgress) {
                schedulingWithProgress(observable)
            } else {
                scheduling(observable)
            }
                .subscribe(
                    { result.invoke(it) },
                    { error.invoke(it) }
                )
        )
    }

    protected fun <T> runAsync(
        flowable: Flowable<T>,
        result: (result: T) -> Unit,
        error: (throwable: Throwable) -> Unit = { showError(it) },
        showProgress: Boolean = false
    ) {
        addDisposable(
            if (showProgress) {
                schedulingWithProgress(flowable)
            } else {
                scheduling(flowable)
            }
                .subscribe(
                    { result.invoke(it) },
                    { error.invoke(it) }
                )
        )
    }

    private fun <T> scheduling(single: Single<T>): Single<T> {
        return single.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    private fun scheduling(completable: Completable): Completable {
        return completable.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    private fun <T> scheduling(observable: Observable<T>): Observable<T> {
        return observable.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    private fun <T> scheduling(flowable: Flowable<T>): Flowable<T> {
        return flowable.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    private fun <T> schedulingWithProgress(single: Single<T>): Single<T> {
        return scheduling(single)
            .doOnSubscribe { progressOn() }
            .doAfterTerminate { progressOff() }
    }

    private fun schedulingWithProgress(completable: Completable): Completable {
        return scheduling(completable)
            .doOnSubscribe { progressOn() }
            .doAfterTerminate { progressOff() }
    }

    private fun <T> schedulingWithProgress(observable: Observable<T>): Observable<T> {
        return scheduling(observable)
            .doOnSubscribe { progressOn() }
            .doOnNext { progressOff() }
            .doAfterTerminate { progressOff() }
    }

    private fun <T> schedulingWithProgress(flowable: Flowable<T>): Flowable<T> {
        return scheduling(flowable)
            .doOnSubscribe { progressOn() }
            .doAfterTerminate { progressOff() }
    }

    private fun addDisposable(disposable: Disposable) {
        compositeDisposable.add(disposable)
    }

    private fun progressOn() {
        showProgress()
    }

    private fun progressOff() {
        hideProgress()
    }
}