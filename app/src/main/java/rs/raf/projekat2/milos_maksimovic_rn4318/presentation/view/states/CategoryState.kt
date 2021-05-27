package rs.raf.projekat2.milos_maksimovic_rn4318.presentation.view.states

import rs.raf.projekat2.milos_maksimovic_rn4318.data.models.ui.FoodCategory

sealed class CategoryState {
    object Loading : CategoryState()
    object DataFetched : CategoryState()
    data class Success(var categories: List<FoodCategory>) : CategoryState()
    data class Error(val message: String) : CategoryState()
}