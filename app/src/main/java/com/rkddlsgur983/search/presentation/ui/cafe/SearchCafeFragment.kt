package com.rkddlsgur983.search.presentation.ui.cafe

import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.EditorInfo
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.rkddlsgur983.search.R
import com.rkddlsgur983.search.databinding.FragmentSearchCafeBinding
import com.rkddlsgur983.search.presentation.cafe.SearchCafeViewModel
import com.rkddlsgur983.search.presentation.ui.cafe.adapter.SearchCafeAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SearchCafeFragment : Fragment(R.layout.fragment_search_cafe) {

    private val viewModel by viewModels<SearchCafeViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentSearchCafeBinding.bind(view)
        val searchCafeAdapter = SearchCafeAdapter()
        initView(binding, searchCafeAdapter)
        initViewModel(searchCafeAdapter)
    }

    private fun initView(
        binding: FragmentSearchCafeBinding,
        searchCafeAdapter: SearchCafeAdapter
    ) {
        binding.vm = viewModel
        binding.lifecycleOwner = this
        binding.etSearchCafe.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                viewModel.onSearchClick()
                true
            } else {
                false
            }
        }
        binding.etSearchCafe.setOnKeyListener { _, keyCode, event ->
            if (event.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
                viewModel.onSearchClick()
                true
            } else {
                false
            }
        }
        binding.ivSearchCafe.setOnClickListener {
            viewModel.onSearchClick()
        }
        binding.rvSearchCafe.adapter = searchCafeAdapter
    }

    private fun initViewModel(
        searchCafeAdapter: SearchCafeAdapter
    ) {
        lifecycleScope.launch {
            viewModel.documentListStateFlow.collectLatest { pagingData ->
                searchCafeAdapter.submitData(pagingData)
            }
        }
    }
}
