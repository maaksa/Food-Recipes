package rs.raf.projekat2.milos_maksimovic_rn4318.presentation.view.states

import rs.raf.projekat2.milos_maksimovic_rn4318.data.models.ui.Food
import rs.raf.projekat2.milos_maksimovic_rn4318.data.models.ui.FoodCategory
import rs.raf.projekat2.milos_maksimovic_rn4318.data.models.ui.FoodRecipe

sealed class FoodRecipesState {
    object Loading : FoodRecipesState()
    object DataFetched : FoodRecipesState()
    data class Success(var foodRecipe: FoodRecipe) : FoodRecipesState()
    data class Error(val message: String) : FoodRecipesState()
}