package com.example.myapplication.ui.features.login.view

import android.os.Bundle
import androidx.core.widget.doAfterTextChanged
import com.example.myapplication.R
import com.example.myapplication.databinding.ActivityLoginBinding
import com.example.myapplication.ui.base.activity.BaseMvpActivity
import com.example.myapplication.ui.base.utils.gone
import com.example.myapplication.ui.base.utils.visible
import com.example.myapplication.ui.features.login.di.DaggerLoginActivityComponent
import com.example.myapplication.ui.features.login.di.LoginActivityModule
import com.example.myapplication.ui.features.login.presenter.ILoginActivityPresenter


class LoginActivity : BaseMvpActivity<ActivityLoginBinding, ILoginView, ILoginActivityPresenter>(),
    ILoginView {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initListeners()
    }

    override fun navigateMenu() {
        router.navigateMenu(this)
    }

    override fun injectDependencies() {
        DaggerLoginActivityComponent.builder()
            .applicationComponent(applicationComponent)
            .loginActivityModule(LoginActivityModule())
            .build()
            .inject(this)
    }

    override fun hideProgress() {
        binding.progress.gone()
        binding.loginForm.visible()
    }

    override fun showProgress() {
        binding.progress.visible()
        binding.loginForm.gone()
    }

    override val layoutResId: Int
        get() = R.layout.activity_login

    private fun initListeners() {
        with(binding) {
            signInButton.setOnClickListener {
                presenter.onSignInClicked(
                    login = binding.loginEditText.text.toString(),
                    password = binding.passwordEditText.text.toString()
                )
            }
            loginEditText.doAfterTextChanged {
                signInButton.isEnabled = loginEditText.text.toString().isEmpty().not() &&
                        passwordEditText.text.isEmpty().not()
            }
            passwordEditText.doAfterTextChanged {
                signInButton.isEnabled = loginEditText.text.toString().isEmpty().not() &&
                        passwordEditText.text.isEmpty().not()
            }
        }
    }

}