package com.rkddlsgur983.search.presentation.ui.cafe

import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.rkddlsgur983.search.R
import com.rkddlsgur983.search.databinding.FragmentSearchCafeBinding
import com.rkddlsgur983.search.presentation.ui.cafe.adapter.SearchCafeAdapter
import dagger.hilt.android.AndroidEntryPoint

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
            when (actionId) {
                EditorInfo.IME_ACTION_SEARCH -> {
                    viewModel.searchCafe()
                    true
                }
                else -> false
            }
        }
        binding.ivSearchCafe.setOnClickListener {
            viewModel.searchCafe()
        }
        binding.rvSearchCafe.adapter = searchCafeAdapter
    }

    private fun initViewModel(
        searchCafeAdapter: SearchCafeAdapter
    ) {
        viewModel.documentListLiveData.observe(viewLifecycleOwner) {
            searchCafeAdapter.submitList(it)
        }
    }
}
