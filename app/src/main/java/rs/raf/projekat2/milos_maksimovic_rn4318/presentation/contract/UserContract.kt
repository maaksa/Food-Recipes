package rs.raf.projekat2.milos_maksimovic_rn4318.presentation.contract

import androidx.lifecycle.LiveData
import rs.raf.projekat2.milos_maksimovic_rn4318.data.models.ui.FoodRecipe
import rs.raf.projekat2.milos_maksimovic_rn4318.data.models.ui.User
import rs.raf.projekat2.milos_maksimovic_rn4318.presentation.view.states.*

interface UserContract {

    interface ViewModel {

        val userState: LiveData<UserState>
        val addDone: LiveData<AddUserState>

        fun getByPin()
        fun addUser(user: User)
    }
}