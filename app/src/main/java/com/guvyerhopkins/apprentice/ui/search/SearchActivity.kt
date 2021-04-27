package com.guvyerhopkins.apprentice.ui.search

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.guvyerhopkins.apprentice.R

class SearchActivity : AppCompatActivity() {

    private lateinit var searchViewModel: SearchViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
        setSupportActionBar(findViewById(R.id.toolbar))

        searchViewModel =
            ViewModelProvider(this, SearchViewModelFactory()).get(SearchViewModel::class.java)
        val adapter = PhotoGridAdapter()
        val recyclerView = findViewById<RecyclerView>(R.id.search_rv)
        recyclerView.adapter = adapter
    }
}