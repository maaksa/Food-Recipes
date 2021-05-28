package rs.raf.projekat2.milos_maksimovic_rn4318.data.models.api.food


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

data class FoodResponse(

    val recipe_id: String,
    val image_url: String,
    val social_rank: Double,
    val publisher: String,
    val title: String

)