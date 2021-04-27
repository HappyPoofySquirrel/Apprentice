package com.guvyerhopkins.apprentice.network

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import kotlinx.coroutines.*

class PexelsDataSource(private val query: String, private val scope: CoroutineScope) :
    PageKeyedDataSource<Int, Photo>() {

    private val repo = PexelsRepository(PexelsApi.retrofitService)
    private val networkState = MutableLiveData<State>()
    private var supervisorJob = SupervisorJob()

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, Photo>
    ) {
        executeQuery(1, params.requestedLoadSize) {
            callback.onResult(it, null, 2)
        }
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Photo>) {
        val page = params.key //todo might need to check for being on first page
        executeQuery(page, params.requestedLoadSize) {
            callback.onResult(it, page - 1)
        }
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Photo>) {
        val page = params.key
        executeQuery(page, params.requestedLoadSize) {
            callback.onResult(it, page + 1)
        }
    }

    private fun executeQuery(page: Int, perPage: Int, callback: (List<Photo>) -> Unit) {
        scope.launch(getJobErrorHandler() + supervisorJob) {
            delay(200) // To handle user typing case
            val photos = repo.getPhotos(page, perPage, query)
            // ...
            callback(photos)
        }
    }

    private fun getJobErrorHandler() = CoroutineExceptionHandler { _, e ->
        Log.e(PexelsResponse::class.java.simpleName, "An error happened: $e")
        networkState.postValue(State.ERROR)
    }

    override fun invalidate() {
        super.invalidate()
        supervisorJob.cancelChildren()   // Cancel possible running job to only keep last result searched by user
    }

    fun refresh() = this.invalidate()

    fun getNetworkState(): LiveData<State> =
        networkState
}
 