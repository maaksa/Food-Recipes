package rs.raf.projekat2.milos_maksimovic_rn4318.data.models.api.cateogry

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CategoryResponse(
    @Json(name = "imageUrl")
    val imageUrl: String,
    @Json(name = "title")
    val title: String
)