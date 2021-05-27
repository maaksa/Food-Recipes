package rs.raf.projekat2.milos_maksimovic_rn4318.presentation.view.states

import rs.raf.projekat2.milos_maksimovic_rn4318.data.models.ui.Food
import rs.raf.projekat2.milos_maksimovic_rn4318.data.models.ui.FoodCategory

sealed class FoodState {
    object Loading : FoodState()
    object DataFetched : FoodState()
    data class Success(var foods: List<Food>) : FoodState()
    data class Error(val message: String) : FoodState()
}