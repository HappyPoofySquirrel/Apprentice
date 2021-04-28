package com.guvyerhopkins.apprentice.network

class PexelsRepository(private val service: PexelsApiService) {

    private suspend fun search(page: Int, perPage: Int, query: String) =
        service.getPhotos(query, page, perPage)

    suspend fun getPhotos(
        page: Int,
        perPage: Int,
        query: String
    ): List<Photo> {
        if (query.isEmpty()) return listOf()
        val request = search(page, perPage, query)
        return request.photos
    }
}