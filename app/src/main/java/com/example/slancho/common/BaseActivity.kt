package com.example.slancho.common

import android.content.Context
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.example.slancho.BuildConfig
import com.example.slancho.R
import com.example.slancho.databinding.ActivityBaseBinding
import com.example.slancho.di.Injectable
import com.example.slancho.utils.PermissionsManager
import com.example.slancho.utils.VibrationManager
import com.google.firebase.auth.FirebaseAuth
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

/**
 * This Activity is to be inherited by any activity to initiate the injection.
 */
abstract class BaseActivity<B : ViewDataBinding> : DaggerAppCompatActivity(), Injectable {

    @Inject lateinit var viewModelFactory: ViewModelProvider.Factory
    @Inject lateinit var firebaseAuth: FirebaseAuth
    @Inject lateinit var vibrationManager: VibrationManager
    @Inject lateinit var permissionsManager: PermissionsManager

    private lateinit var inheritanceBinding: B
    private lateinit var baseBinding: ActivityBaseBinding

    protected fun <T : ViewModel> getViewModel(cls: Class<T>): T {
        return ViewModelProviders.of(this, viewModelFactory).get(cls)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        baseBinding = DataBindingUtil.setContentView(this, R.layout.activity_base)
        inheritanceBinding =
            DataBindingUtil.inflate(
                layoutInflater,
                getLayoutResId(),
                baseBinding.grpContentBase,
                true
            )
        initFields()
        initViews()
        initListeners()
    }

    fun getBinding() = inheritanceBinding

    protected abstract fun initFields()

    protected abstract fun initViews()

    protected abstract fun initListeners()

    protected abstract fun getLayoutResId(): Int

    protected abstract val TAG: String


    fun hideKeyboard() {
        if (currentFocus != null) {
            val inputMethodManager =
                getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(currentFocus!!.windowToken, 0)
        }
    }

    protected fun getAppVersion(): String =
        getString(R.string.app_version, BuildConfig.VERSION_NAME)
}
