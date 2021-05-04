package com.guvyerhopkins.apprentice.ui.search

import com.guvyerhopkins.apprentice.network.PexelsDataSourceFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.*

class SearchViewModelTest {

    private lateinit var viewModel: SearchViewModel

    private val dataSourceFactory = mock(PexelsDataSourceFactory::class.java)

    @Before
    fun setUp() {
        viewModel = SearchViewModel(CoroutineScope(Dispatchers.Main), dataSourceFactory)
    }

    @Test
    fun onSearch_doesNotUpdateQuery() {
        `when`(dataSourceFactory.query).thenReturn("test")
        viewModel.search("test")
        verify(dataSourceFactory, times(0)).updateQuery(anyString())
    }

    @Test
    fun onSearch_updateQuery() {
        `when`(dataSourceFactory.query).thenReturn("test")
        viewModel.search("testt")
        verify(dataSourceFactory, times(1)).updateQuery(anyString())
    }
}