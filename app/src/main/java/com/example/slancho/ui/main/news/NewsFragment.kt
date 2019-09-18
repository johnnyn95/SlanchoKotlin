package com.example.slancho.ui.main.news

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.slancho.R
import com.example.slancho.common.BaseFragment
import com.example.slancho.databinding.FragmentNewsBinding

class NewsFragment : BaseFragment() {
    override val fragmentTag: String get() = NewsFragment::class.toString()

    lateinit var binding: FragmentNewsBinding

    companion object {
        fun newInstance(): NewsFragment {
            return NewsFragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_news, container, false)
        initFields()
        initViews()
        initListeners()
        return binding.root
    }

    override fun initFields() {}

    override fun initViews() {}

    override fun initListeners() {}
}


