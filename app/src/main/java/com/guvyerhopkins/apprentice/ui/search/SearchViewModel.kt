package com.guvyerhopkins.apprentice.ui.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.guvyerhopkins.apprentice.network.PexelsDataSourceFactory
import com.guvyerhopkins.apprentice.network.State
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel


class SearchViewModel : ViewModel() {

    private val ioScope = CoroutineScope(Dispatchers.Default)

    private val dataSourceFactory = PexelsDataSourceFactory(scope = ioScope)

    private val _state = MutableLiveData<State>()
    val status: LiveData<State> = _state

    val photos = LivePagedListBuilder(
        dataSourceFactory, PagedList.Config.Builder()
            .setInitialLoadSizeHint(5)
            .setEnablePlaceholders(false)
            .setPageSize(5 * 2)
            .build()
    )

    init {
//        _state.value = State.LOADING
//        viewModelScope.launch {
//            try {
//                _photos.value = MarsApi.retrofitService.getPhotos()
//                _state.value =State.SUCCESS
//            } catch (e: Exception) {
//                _state.value = State.ERROR
//                _photos.value = listOf()
//            }
    }

    fun search(query: String) {
        //if() //return if query did not change
        dataSourceFactory.updateQuery(query.trim())
    }

    override fun onCleared() {
        super.onCleared()
        ioScope.coroutineContext.cancel()
    }
}