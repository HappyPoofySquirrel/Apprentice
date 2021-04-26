package com.guvyerhopkins.apprentice.network

import com.guvyerhopkins.apprentice.BuildConfig.PEXELS_API_BASE_URL
import com.guvyerhopkins.apprentice.BuildConfig.PEXELS_API_KEY
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

private val retrofit = Retrofit.Builder()
    .addConverterFactory(GsonConverterFactory.create())
    .client(okHttpClient)
    .baseUrl(PEXELS_API_BASE_URL)
    .build()

private val okHttpClient = OkHttpClient.Builder().apply {
    addInterceptor(
        Interceptor { chain ->
            val builder = chain.request().newBuilder()
            builder.header("Authorization", PEXELS_API_KEY)
            return@Interceptor chain.proceed(builder.build())
        }
    )
}.build()

interface PexelsApiService {
    @GET("search")
    suspend fun getPhotos(
        @Query("page") page: Int,
        @Query("pageSize") pageSize: Int,
        @Query("query") query: String
    ): List<Photo>
}

object PexelsApi {
    val retrofitService: PexelsApiService by lazy {
        retrofit.create(PexelsApiService::class.java)
    }
}
