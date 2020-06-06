package com.example.myapplication.ui.features.login.view

import android.os.Bundle
import com.example.myapplication.R
import com.example.myapplication.databinding.ActivityLoginBinding
import com.example.myapplication.ui.base.activity.BaseMvpActivity
import com.example.myapplication.ui.features.login.di.DaggerLoginFragmentComponent
import com.example.myapplication.ui.features.login.di.LoginFragmentModule
import com.example.myapplication.ui.features.login.presenter.ILoginFragmentPresenter


class LoginActivity : BaseMvpActivity<ActivityLoginBinding, ILoginView, ILoginFragmentPresenter>() {

    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        with(binding) {
            signInButton.setOnClickListener {
                presenter.onSignInClicked(
                    login = binding.loginEditText.text.toString(),
                    password = binding.passwordEditText.text.toString()
                )
            }
            /*loginEditText.doAfterTextChanged {
                signInButton.isEnabled = loginEditText.text.toString().isEmpty().not() &&
                        passwordEditText.text.isEmpty().not()
            }
            passwordEditText.doAfterTextChanged {
                signInButton.isEnabled = loginEditText.text.toString().isEmpty().not() &&
                        passwordEditText.text.isEmpty().not()
            }*/
        }
    }


    override fun injectDependencies() {
        DaggerLoginFragmentComponent.builder()
            .applicationComponent(applicationComponent)
            .loginFragmentModule(LoginFragmentModule())
            .build()
            .inject(this)
    }

    override fun hideProgress() {
        //
    }

    override fun showProgress() {
        //
    }

    override val layoutResId: Int
        get() = R.layout.activity_login

}