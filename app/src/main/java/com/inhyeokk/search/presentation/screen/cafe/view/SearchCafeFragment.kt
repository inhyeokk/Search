package com.inhyeokk.search.presentation.screen.cafe.view

import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.EditorInfo
import androidx.core.view.isVisible
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import com.inhyeokk.search.R
import com.inhyeokk.search.databinding.FragmentSearchCafeBinding
import com.inhyeokk.search.extension.launchWithLifecycle
import com.inhyeokk.search.presentation.screen.cafe.SearchCafeViewModel
import com.inhyeokk.search.presentation.screen.cafe.Event
import com.inhyeokk.search.presentation.screen.cafe.view.adapter.SearchCafeAdapter
import com.inhyeokk.search.presentation.screen.cafe.view.adapter.SearchCafeLoadStateAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.distinctUntilChangedBy
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SearchCafeFragment : Fragment(R.layout.fragment_search_cafe) {
    private val viewModel by viewModels<SearchCafeViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentSearchCafeBinding.bind(view)
        val searchCafeAdapter = SearchCafeAdapter()
        initView(binding)
        initSearchCafeAdapter(binding, searchCafeAdapter)
        initViewModel(searchCafeAdapter)
    }

    private fun initView(binding: FragmentSearchCafeBinding) {
        binding.etSearchCafe.doAfterTextChanged { text ->
            text?.let { viewModel.setQuery(it.toString()) }
        }
        binding.etSearchCafe.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                viewModel.onClickSearch()
                true
            } else {
                false
            }
        }
        binding.etSearchCafe.setOnKeyListener { _, keyCode, event ->
            if (event.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
                viewModel.onClickSearch()
                true
            } else {
                false
            }
        }
        binding.btnRetry.setOnClickListener {
            viewModel.onClickRetry()
        }
    }

    private fun initSearchCafeAdapter(
        binding: FragmentSearchCafeBinding,
        searchCafeAdapter: SearchCafeAdapter
    ) {
        searchCafeAdapter.addLoadStateListener { loadState ->
            val hasList =
                loadState.refresh !is LoadState.NotLoading || searchCafeAdapter.itemCount > 0
            binding.rvSearchCafe.isVisible = hasList
            binding.tvEmpty.isVisible = !hasList
        }
        binding.rvSearchCafe.adapter = searchCafeAdapter.withLoadStateHeaderAndFooter(
            header = SearchCafeLoadStateAdapter { searchCafeAdapter.retry() },
            footer = SearchCafeLoadStateAdapter { searchCafeAdapter.retry() }
        )
        lifecycleScope.launch {
            searchCafeAdapter.loadStateFlow
                .distinctUntilChangedBy { it.refresh } // refresh 값이 변경되는 시점을 트리거
                .filter { it.refresh is LoadState.NotLoading } // refresh 가 시작되기 전 NotLoading
                .collect { binding.rvSearchCafe.scrollToPosition(0) }
        }
    }

    private fun initViewModel(searchCafeAdapter: SearchCafeAdapter) {
        viewModel.eventFlow.launchWithLifecycle(fragment = this) {
            when (it) {
                is Event.OnClickRetry -> {
                    searchCafeAdapter.retry()
                }
                else -> {
                }
            }
        }
        viewModel.documentListStateFlow.launchWithLifecycle(fragment = this) { pagingData ->
            searchCafeAdapter.submitData(pagingData)
        }
    }
}
