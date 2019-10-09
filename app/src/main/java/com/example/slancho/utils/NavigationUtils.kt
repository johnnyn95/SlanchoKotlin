package com.example.slancho.utils

import android.content.Context
import android.content.Intent
import com.example.slancho.ui.main.MainActivity
import com.example.slancho.ui.main.MainActivity.Companion.EXTRA_USER_ID
import com.example.slancho.ui.signIn.SignInActivity
import com.example.slancho.ui.signUp.SignUpActivity

sealed class NavigationUtils {
    companion object {
        fun initMainActivityIntent(context: Context, userId: String): Intent {
            val intent = Intent(context, MainActivity::class.java)
            intent.putExtra(EXTRA_USER_ID, userId)
            return intent
        }

        fun initSignInActivityIntent(context: Context): Intent {
            return Intent(context, SignInActivity::class.java)
        }

        fun initSignUpActivityIntent(context: Context): Intent {
            return Intent(context, SignUpActivity::class.java)
        }
    }

}