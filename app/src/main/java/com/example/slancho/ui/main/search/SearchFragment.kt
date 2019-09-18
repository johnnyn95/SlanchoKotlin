package com.example.slancho.ui.main.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.slancho.R
import com.example.slancho.common.BaseFragment
import com.example.slancho.databinding.FragmentSearchBinding

class SearchFragment : BaseFragment() {
    override val fragmentTag: String get() = SearchFragment::class.toString()

    lateinit var binding: FragmentSearchBinding

    companion object {
        fun newInstance(): SearchFragment {
            return SearchFragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_search, container, false)
        initFields()
        initViews()
        initListeners()
        return binding.root
    }

    override fun initFields() {}

    override fun initViews() {}

    override fun initListeners() {}
}


