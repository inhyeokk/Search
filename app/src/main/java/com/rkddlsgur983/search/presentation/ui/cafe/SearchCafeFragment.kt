package com.rkddlsgur983.search.presentation.ui.cafe

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.rkddlsgur983.search.R
import com.rkddlsgur983.search.databinding.FragmentSearchCafeBinding

class SearchCafeFragment : Fragment(R.layout.fragment_search_cafe) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentSearchCafeBinding.bind(view)
    }
}
