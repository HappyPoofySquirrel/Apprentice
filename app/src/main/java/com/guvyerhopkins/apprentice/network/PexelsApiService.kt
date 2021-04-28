package com.guvyerhopkins.apprentice.network

import com.guvyerhopkins.apprentice.BuildConfig.PEXELS_API_BASE_URL
import com.guvyerhopkins.apprentice.BuildConfig.PEXELS_API_KEY
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

private val retrofit = Retrofit.Builder()
    .addConverterFactory(GsonConverterFactory.create())
    .client(OkHttpClient.Builder().apply {
        addInterceptor(
            Interceptor { chain ->
                val builder = chain.request().newBuilder()
                builder.header("Authorization", PEXELS_API_KEY)
                return@Interceptor chain.proceed(builder.build())
            }
        )
        addInterceptor(HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        })
    }.build())
    .baseUrl(PEXELS_API_BASE_URL)
    .build()

interface PexelsApiService {
    @GET("search")
    suspend fun getPhotos(
        @Query("query") query: String,
        @Query("page") page: Int,
        @Query("per_page") perPage: Int
    ): PexelsResponse
}

object PexelsApi {
    val retrofitService: PexelsApiService by lazy {
        retrofit.create(PexelsApiService::class.java)
    }
}
