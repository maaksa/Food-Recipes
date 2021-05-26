package rs.raf.projekat2.milos_maksimovic_rn4318.data.models.api.recipe

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class FoodRecipeDetailsResponse(
    @Json(name = "recipe")
    val recipeDetails: FoodRecipeDetailResponse
)