package rs.raf.projekat2.milos_maksimovic_rn4318.data.models.api.food

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

data class FoodsResponse(
    val count: Int,
    val recipes: List<FoodResponse>
)