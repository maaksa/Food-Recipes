package rs.raf.projekat2.milos_maksimovic_rn4318.data.datasources.remote

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query
import rs.raf.projekat2.milos_maksimovic_rn4318.data.models.api.recipe.FoodRecipeDetailsResponse
import rs.raf.projekat2.milos_maksimovic_rn4318.data.models.api.food.FoodsResponse

interface FoodService {

    @GET("search")
    fun getAll(
        @Query("q") querySearch: String,
        @Query("page") page: Int = 1
    ): Observable<FoodsResponse>

    @GET("get")
    fun getById(@Query("rId") recipeId: Int): Observable<FoodRecipeDetailsResponse>

}