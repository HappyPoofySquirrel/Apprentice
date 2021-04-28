package com.guvyerhopkins.apprentice.network

import com.google.gson.InstanceCreator
import com.google.gson.annotations.SerializedName
import java.lang.reflect.Type

data class PexelsResponse(
    @SerializedName("next_page")
    val nextPage: String = "",
    @SerializedName("page")
    val page: Int = 0,
    @SerializedName("per_page")
    val perPage: Int = 0,
    @SerializedName("photos")
    val photos: List<Photo> = listOf(),
    @SerializedName("total_results")
    val totalResults: Int = 0
)

data class Photo(
    @SerializedName("avg_color")
    val avgColor: String = "",
    @SerializedName("height")
    val height: Int = 0,
    @SerializedName("id")
    val id: Int = 0,
    @SerializedName("liked")
    val liked: Boolean = false,
    @SerializedName("photographer")
    val photographer: String = "",
    @SerializedName("photographer_id")
    val photographerId: Int = 0,
    @SerializedName("photographer_url")
    val photographerUrl: String = "",
    @SerializedName("src")
    val src: Src = Src(),
    @SerializedName("url")
    val url: String = "",
    @SerializedName("width")
    val width: Int = 0
)

data class Src(
    @SerializedName("landscape")
    val landscape: String = "",
    @SerializedName("large")
    val large: String = "",
    @SerializedName("large2x")
    val large2x: String = "",
    @SerializedName("medium")
    val medium: String = "",
    @SerializedName("original")
    val original: String = "",
    @SerializedName("portrait")
    val portrait: String = "",
    @SerializedName("small")
    val small: String = "",
    @SerializedName("tiny")
    val tiny: String = ""
)

class PexelsResponseAdapter :
    InstanceCreator<PexelsResponse> {
    override fun createInstance(type: Type?): PexelsResponse {
        return PexelsResponse()
    }
}