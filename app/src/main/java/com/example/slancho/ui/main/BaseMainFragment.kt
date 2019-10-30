package com.example.slancho.ui.main

import com.example.slancho.common.BaseFragment
import com.example.slancho.db.model.User

abstract class BaseMainFragment : BaseFragment(){
    lateinit var currentUser: User
}