package com.github.rkddlsgur983.search.presentation

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.github.rkddlsgur983.search.databinding.ActivityMainBinding
import com.github.rkddlsgur983.search.presentation.compose.SearchComposeActivity
import com.github.rkddlsgur983.search.presentation.ui.SearchActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnOpenSearch.setOnClickListener {
            startActivity(Intent(this, SearchActivity::class.java))
        }
        binding.btnOpenSearchCompose.setOnClickListener {
            startActivity(Intent(this, SearchComposeActivity::class.java))
        }
    }
}
