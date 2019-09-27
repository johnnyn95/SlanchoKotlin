package com.example.slancho.ui.signIn

import android.content.Intent
import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT
import com.example.slancho.R
import com.example.slancho.common.BaseActivity
import com.example.slancho.databinding.ActivitySignInBinding
import com.example.slancho.ui.main.MainActivity
import com.example.slancho.ui.signUp.SignUpActivity
import dagger.android.AndroidInjection
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SignInActivity : BaseActivity<ActivitySignInBinding>() {

    private lateinit var viewModel: SignInActivityViewModel
    private lateinit var email: String
    private lateinit var password: String

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun initFields() {
        viewModel = getViewModel(SignInActivityViewModel::class.java)
    }

    override fun initViews() {
        getBinding().txtVersion.text = getAppVersion()
    }

    override fun initListeners() {
        getBinding().btnSignIn.setOnClickListener {
            signInWithEmailAndPassword()
            hideKeyboard()
        }
        getBinding().btnSignInAnonymously.setOnClickListener { loginAnonymously() }
        getBinding().btnSignUp.setOnClickListener { navigateToSignUp() }
    }

    override fun getLayoutResId(): Int = R.layout.activity_sign_in

    private fun signInWithEmailAndPassword() {
        if (validateInputFields()) {
            firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) {
                    if (it.isSuccessful) {
                        CoroutineScope(Dispatchers.IO).launch {
                            viewModel.signInWithEmailAndPassword(firebaseAuth.currentUser!!)
                        }
                        signInSuccessfulToast()
                        navigateToMain()

                    } else {
                        signInFailedToast()
                    }
                }
        } else invalidInputFieldsAnimation()
    }

    private fun loginAnonymously() {
        firebaseAuth.signInAnonymously().addOnCompleteListener(this) {
            if (it.isSuccessful) {
                CoroutineScope(Dispatchers.IO).launch {
                    viewModel.signInAnonymously(firebaseAuth.currentUser!!)
                }
                signInSuccessfulToast()
                navigateToMain()

            } else {
                signInFailedToast()
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

    private fun navigateToSignUp() = startActivity(Intent(this, SignUpActivity::class.java))

    private fun signInSuccessfulToast() =
        Toast.makeText(baseContext, R.string.sign_in_successful, LENGTH_SHORT).show()

    private fun signInFailedToast() =
        Toast.makeText(baseContext, R.string.sign_in_failed, LENGTH_SHORT).show()
}
