package rs.raf.projekat2.milos_maksimovic_rn4318.presentation.view.states

sealed class AddFoodState {

    object Success: AddFoodState()
    data class Error(val message: String): AddFoodState()

}