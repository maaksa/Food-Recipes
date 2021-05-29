package rs.raf.projekat2.milos_maksimovic_rn4318.presentation.contract

import androidx.lifecycle.LiveData
import rs.raf.projekat2.milos_maksimovic_rn4318.data.models.ui.Food
import rs.raf.projekat2.milos_maksimovic_rn4318.data.models.ui.FoodRecipe
import rs.raf.projekat2.milos_maksimovic_rn4318.presentation.view.states.AddFoodState
import rs.raf.projekat2.milos_maksimovic_rn4318.presentation.view.states.CategoryState
import rs.raf.projekat2.milos_maksimovic_rn4318.presentation.view.states.FoodState

interface FoodContract {

    interface ViewModel {

        val foodState: LiveData<FoodState>
        val addDone: LiveData<AddFoodState>

        fun fetchAllFoods(querySearch: String, page: Int)
        fun getAllByName(title: String)
        fun addFood(food: Food)

    }
}