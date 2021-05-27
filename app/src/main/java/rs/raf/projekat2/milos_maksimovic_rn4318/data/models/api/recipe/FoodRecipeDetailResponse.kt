package rs.raf.projekat2.milos_maksimovic_rn4318.data.models.api.recipe

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

data class FoodRecipeDetailResponse(

    val ingredients: List<String>,
    val image_url: String,
    val social_rank: Double,
    val publisher: String,
    val recipe_id: String,
    val title: String
)