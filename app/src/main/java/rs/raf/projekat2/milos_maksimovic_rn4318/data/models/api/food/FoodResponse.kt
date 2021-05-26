package rs.raf.projekat2.milos_maksimovic_rn4318.data.models.api.food

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class FoodResponse(

    @Json(name = "image_url")
    val imageURL: String,
    @Json(name = "social_rank")
    val socialRank: Double,
    @Json(name = "publisher")
    val publisher: String,
    @Json(name = "recipe_id")
    val recipeId: String,
    @Json(name = "source_url")
    val sourceURL: String,
    @Json(name = "title") val
    title: String

)