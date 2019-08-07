package com.example.slancho.common

import android.content.Context
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.example.slancho.R
import com.example.slancho.databinding.ActivityBaseBinding
import com.example.slancho.di.Injectable
import javax.inject.Inject

/**
 * This Activity is to be inherited by any activity to initiate the injection.
 */
abstract class BaseActivity<B : ViewDataBinding> : AppCompatActivity(), Injectable {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    protected lateinit var inheritanceBinding: B

    protected lateinit var baseBinding: ActivityBaseBinding

    protected fun <T : ViewModel> getViewModel(cls: Class<T>): T {
        return ViewModelProviders.of(this, viewModelFactory).get(cls)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        baseBinding = DataBindingUtil.setContentView(this, R.layout.activity_base)
        inheritanceBinding =
            DataBindingUtil.inflate(layoutInflater, getLayoutResId(), baseBinding.grpContentBase, true)
        initFields()
        initViews()
        initListeners()
    }

    fun getBinding() = inheritanceBinding

    protected abstract fun initFields()

    protected abstract fun initViews()

    protected abstract fun initListeners()

    protected abstract fun getLayoutResId(): Int

    fun hideKeyboard() {
        if (currentFocus != null) {
            val inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(currentFocus!!.windowToken, 0)
        }
    }
}
