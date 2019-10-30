package com.example.slancho.ui.main.news

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.slancho.R
import com.example.slancho.databinding.FragmentNewsBinding
import com.example.slancho.db.model.User
import com.example.slancho.ui.main.BaseMainFragment

class NewsFragment : BaseMainFragment() {
    lateinit var binding: FragmentNewsBinding
    lateinit var viewModel: NewsFragmentViewModel

    companion object {
        fun newInstance(user: User): NewsFragment {
            val newsFragment = NewsFragment()
            newsFragment.currentUser = user
            return newsFragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_news, container, false
        )
        initFields()
        initViews()
        initListeners()
        viewModel.onScreenReady(currentUser)
        return binding.root
    }

    override fun initFields() {
        viewModel = getViewModel(NewsFragmentViewModel::class.java)
    }

    override fun initViews() {}

    override fun initListeners() {}
}


