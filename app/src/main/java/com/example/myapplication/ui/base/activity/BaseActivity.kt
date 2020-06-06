package com.example.myapplication.ui.base.activity

import android.os.Bundle
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.example.myapplication.ui.base.utils.Router
import com.example.myapplication.ui.base.utils.ToastShower
import javax.inject.Inject

abstract class BaseActivity<B : ViewDataBinding> : AppCompatActivity() {

    @Inject
    lateinit var router: Router

    @Inject
    lateinit var toastShower: ToastShower

    lateinit var binding: B

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, layoutResId)
        injectDependencies()
    }

    fun showToast(message: CharSequence) {
        toastShower.showToast(message)
    }

    fun showToast(@StringRes stringId: Int) {
        toastShower.showToast(getString(stringId))
    }

    abstract val layoutResId: Int

    protected abstract fun injectDependencies()
}