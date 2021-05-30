package rs.raf.projekat2.milos_maksimovic_rn4318.presentation.view.states

sealed class AddUserState {

    object Success : AddUserState()
    data class Error(val message: String) : AddUserState()

}