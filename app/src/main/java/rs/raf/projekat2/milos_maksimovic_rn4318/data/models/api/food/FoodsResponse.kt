package rs.raf.projekat2.milos_maksimovic_rn4318.data.models.api.food

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class FoodsResponse(
    @Json(name = "count")
    val count: Int,
    @Json(name = "recipes")
    val recipes: List<FoodResponse>
)