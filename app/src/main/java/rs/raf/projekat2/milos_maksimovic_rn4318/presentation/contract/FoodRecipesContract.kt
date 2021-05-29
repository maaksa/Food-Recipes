package rs.raf.projekat2.milos_maksimovic_rn4318.presentation.contract

import androidx.lifecycle.LiveData
import rs.raf.projekat2.milos_maksimovic_rn4318.data.models.ui.FoodRecipe
import rs.raf.projekat2.milos_maksimovic_rn4318.presentation.view.states.*

interface FoodRecipesContract {

    interface ViewModel {

        val foodRecipesState: LiveData<FoodRecipesState>
        val addDone: LiveData<AddFoodRecipeState>

        fun fetchRecipeById(id: String)
        fun getRecipeById(id: String)
        fun addFoodRecipe(foodRecip: FoodRecipe)
    }
}