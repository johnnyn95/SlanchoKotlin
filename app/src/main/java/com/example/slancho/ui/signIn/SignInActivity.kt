package com.example.slancho.ui.signIn

import android.content.Intent
import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT
import com.example.slancho.BuildConfig
import com.example.slancho.R
import com.example.slancho.common.BaseActivity
import com.example.slancho.databinding.ActivitySignInBinding
import com.example.slancho.ui.main.MainActivity
import dagger.android.AndroidInjection

class SignInActivity : BaseActivity<ActivitySignInBinding>() {

    lateinit var viewModel: SignInActivityViewModel
    lateinit var email: String
    lateinit var password: String

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun initFields() {
        viewModel = getViewModel(SignInActivityViewModel::class.java)
    }

    override fun initViews() {
        getBinding().txtVersion.text = getString(R.string.app_version, BuildConfig.VERSION_NAME)
    }

    override fun initListeners() {
        getBinding().btnSignIn.setOnClickListener { signInWithEmailAndPassword() }
        getBinding().btnSignInAnonymously.setOnClickListener { loginAnonymously() }
    }

    override fun getLayoutResId(): Int = R.layout.activity_sign_in

    private fun signInWithEmailAndPassword() {
        if (validateInputFields()) {
            firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this) {
                if (it.isSuccessful) {
                    loginSuccessfulToast()
                    navigateToMain()
                } else {
                    loginFailedToast()
                }
            }
        } else invalidInputFieldsAnimation()
    }

    private fun loginAnonymously() {
        firebaseAuth.signInAnonymously().addOnCompleteListener(this) {
            if (it.isSuccessful) {
                loginSuccessfulToast()
                navigateToMain()
            } else {
                loginFailedToast()
            }
        }
    }

    private fun fetchInputFields() {
        email = getBinding().etEmail.text.toString()
        password = getBinding().etPassword.text.toString()
    }

    private fun validateInputFields(): Boolean {
        fetchInputFields()
        return email.isNotEmpty() && password.isNotEmpty()
    }

    private fun invalidInputFieldsAnimation() {
        vibrationManager.failVibration()
        val animation: Animation = AnimationUtils.loadAnimation(this, R.anim.shake)
        getBinding().grpContentBase.startAnimation(animation)
    }

    private fun navigateToMain() = startActivity(Intent(this, MainActivity::class.java))

    private fun loginSuccessfulToast() =
        Toast.makeText(baseContext, R.string.sign_in_failed, LENGTH_SHORT).show()

    private fun loginFailedToast() =
        Toast.makeText(baseContext, R.string.sign_in_failed, LENGTH_SHORT).show()
}
