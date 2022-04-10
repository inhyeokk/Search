package com.github.rkddlsgur983.search.presentation.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.github.rkddlsgur983.search.databinding.ActivitySearchBinding

class SearchActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}
