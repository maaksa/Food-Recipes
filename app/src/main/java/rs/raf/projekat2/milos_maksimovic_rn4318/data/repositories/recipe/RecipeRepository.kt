package rs.raf.projekat2.milos_maksimovic_rn4318.data.repositories.recipe

import io.reactivex.Observable
import rs.raf.projekat2.milos_maksimovic_rn4318.data.models.resources.Resource
import rs.raf.projekat2.milos_maksimovic_rn4318.data.models.ui.FoodRecipe

interface RecipeRepository {

    fun fetchRecipeById(id: String): Observable<Resource<Unit>>

    fun getRecipeById(id: String): Observable<FoodRecipe>

}