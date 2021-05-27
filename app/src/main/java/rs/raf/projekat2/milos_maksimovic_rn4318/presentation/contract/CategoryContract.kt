package rs.raf.projekat2.milos_maksimovic_rn4318.presentation.contract

import androidx.lifecycle.LiveData
import rs.raf.projekat2.milos_maksimovic_rn4318.presentation.view.states.CategoryState

interface CategoryContract {

    interface ViewModel {

        val categoryState: LiveData<CategoryState>

        fun fetchAllCategories()
        fun getAllCategories()
    }
}