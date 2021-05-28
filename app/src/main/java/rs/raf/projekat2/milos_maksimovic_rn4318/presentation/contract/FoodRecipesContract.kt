package rs.raf.projekat2.milos_maksimovic_rn4318.presentation.contract

import androidx.lifecycle.LiveData
import rs.raf.projekat2.milos_maksimovic_rn4318.presentation.view.states.CategoryState
import rs.raf.projekat2.milos_maksimovic_rn4318.presentation.view.states.FoodRecipesState
import rs.raf.projekat2.milos_maksimovic_rn4318.presentation.view.states.FoodState

interface FoodRecipesContract {

    interface ViewModel {

        val foodRecipesState: LiveData<FoodRecipesState>

        fun fetchRecipeById(id: String)
        fun getRecipeById(id: String)
    }
}