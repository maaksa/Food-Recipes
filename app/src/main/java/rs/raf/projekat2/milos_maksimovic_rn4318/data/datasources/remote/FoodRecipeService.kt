package rs.raf.projekat2.milos_maksimovic_rn4318.data.datasources.remote

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query
import rs.raf.projekat2.milos_maksimovic_rn4318.data.models.api.recipe.FoodRecipeDetailsResponse
import rs.raf.projekat2.milos_maksimovic_rn4318.data.models.api.food.FoodsResponse

interface FoodRecipeService {

    @GET("get")
    fun getRecipeById(@Query("rId") recipeId: String): Observable<FoodRecipeDetailsResponse>

}