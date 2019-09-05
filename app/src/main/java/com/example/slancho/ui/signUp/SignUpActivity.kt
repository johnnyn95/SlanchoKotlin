package com.example.slancho.ui.signUp

import android.content.Intent
import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT
import com.example.slancho.BuildConfig
import com.example.slancho.R
import com.example.slancho.common.BaseActivity
import com.example.slancho.databinding.ActivitySignUpBinding
import com.example.slancho.ui.main.MainActivity
import com.example.slancho.ui.signIn.SignInActivity
import dagger.android.AndroidInjection

class SignUpActivity : BaseActivity<ActivitySignUpBinding>() {

    private lateinit var viewModel: SignUpActivityViewModel
    private lateinit var email: String
    private lateinit var password: String
    private lateinit var passwordConfirmation: String


    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun initFields() {
        viewModel = getViewModel(SignUpActivityViewModel::class.java)
    }

    override fun initViews() {
        getBinding().txtVersion.text = getString(R.string.app_version, BuildConfig.VERSION_NAME)
    }

    override fun initListeners() {
        getBinding().btnSignUp.setOnClickListener { signUpWithEmailAndPassword() }
        getBinding().btnSignIn.setOnClickListener { navigateToSignIn() }
        getBinding().btnBack.setOnClickListener { onBackPressed() }
    }

    override fun getLayoutResId(): Int = R.layout.activity_sign_up

    private fun signUpWithEmailAndPassword() {
        if (validateInputFields()) {
            firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) {
                    if (it.isSuccessful) {
                        signUpSuccessfulToast()
                        navigateToMain()
                    } else {
                        signUpFailedToast()
                    }
                }
        } else invalidInputFieldsAnimation()
    }

    private fun fetchInputFields() {
        email = getBinding().etEmail.text.toString()
        password = getBinding().etPassword.text.toString()
        passwordConfirmation = getBinding().etPasswordConfirmation.text.toString()
    }

    private fun validateInputFields(): Boolean {
        fetchInputFields()
        return email.isNotEmpty() &&
                password.isNotEmpty() &&
                passwordConfirmation.isNotEmpty() &&
                passwordConfirmation == password
    }

    private fun invalidInputFieldsAnimation() {
        vibrationManager.failVibration()
        val animation: Animation = AnimationUtils.loadAnimation(this, R.anim.shake)
        getBinding().grpContentBase.startAnimation(animation)
    }

    private fun navigateToMain() = startActivity(Intent(this, MainActivity::class.java))

    private fun navigateToSignIn() = startActivity(Intent(this, SignInActivity::class.java))

    private fun signUpSuccessfulToast() =
        Toast.makeText(baseContext, R.string.sign_up_successful, LENGTH_SHORT).show()

    private fun signUpFailedToast() =
        Toast.makeText(baseContext, R.string.sign_up_failed, LENGTH_SHORT).show()
}
