package rs.raf.projekat2.milos_maksimovic_rn4318.presentation.view.states

sealed class AddFoodRecipeState {

    object Success: AddFoodRecipeState()
    data class Error(val message: String): AddFoodRecipeState()

}