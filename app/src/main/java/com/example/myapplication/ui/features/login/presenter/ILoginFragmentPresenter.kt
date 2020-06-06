package com.example.myapplication.ui.features.login.presenter

import com.example.myapplication.ui.base.presenter.BasePresenter
import com.example.myapplication.ui.features.login.view.ILoginView

abstract class ILoginFragmentPresenter : BasePresenter<ILoginView>() {

    abstract fun onSignInClicked(login: String?, password: String?)

}