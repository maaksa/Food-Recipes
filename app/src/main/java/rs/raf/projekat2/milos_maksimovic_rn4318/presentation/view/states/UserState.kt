package rs.raf.projekat2.milos_maksimovic_rn4318.presentation.view.states

import rs.raf.projekat2.milos_maksimovic_rn4318.data.models.ui.Food
import rs.raf.projekat2.milos_maksimovic_rn4318.data.models.ui.FoodCategory
import rs.raf.projekat2.milos_maksimovic_rn4318.data.models.ui.FoodRecipe
import rs.raf.projekat2.milos_maksimovic_rn4318.data.models.ui.User

sealed class UserState {
    object Loading : UserState()
    object DataFetched : UserState()
    data class Success(var userCnt: Int) : UserState()
    data class Error(val message: String) : UserState()
}