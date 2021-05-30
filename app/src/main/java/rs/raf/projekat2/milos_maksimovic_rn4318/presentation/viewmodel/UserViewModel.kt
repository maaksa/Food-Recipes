package rs.raf.projekat2.milos_maksimovic_rn4318.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import rs.raf.projekat2.milos_maksimovic_rn4318.data.models.resources.Resource
import rs.raf.projekat2.milos_maksimovic_rn4318.data.models.ui.FoodRecipe
import rs.raf.projekat2.milos_maksimovic_rn4318.data.models.ui.User
import rs.raf.projekat2.milos_maksimovic_rn4318.data.repositories.category.CategoryRepository
import rs.raf.projekat2.milos_maksimovic_rn4318.data.repositories.food.FoodRepository
import rs.raf.projekat2.milos_maksimovic_rn4318.data.repositories.recipe.RecipeRepository
import rs.raf.projekat2.milos_maksimovic_rn4318.data.repositories.user.UserRepository
import rs.raf.projekat2.milos_maksimovic_rn4318.presentation.contract.CategoryContract
import rs.raf.projekat2.milos_maksimovic_rn4318.presentation.contract.FoodContract
import rs.raf.projekat2.milos_maksimovic_rn4318.presentation.contract.FoodRecipesContract
import rs.raf.projekat2.milos_maksimovic_rn4318.presentation.contract.UserContract
import rs.raf.projekat2.milos_maksimovic_rn4318.presentation.view.states.*
import timber.log.Timber
import java.util.concurrent.TimeUnit

class UserViewModel(
    private val userRepository: UserRepository,
) : ViewModel(), UserContract.ViewModel {

    private val subscriptions = CompositeDisposable()
    override val userState: MutableLiveData<UserState> = MutableLiveData()
    override val addDone: MutableLiveData<AddUserState> = MutableLiveData()

    override fun getByPin() {
        val subscription = userRepository
            .getByPin()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    userState.value = UserState.Success(it)
                },
                {
                    userState.value =
                        UserState.Error("Error happened while fetching data from db")
                    Timber.e(it)
                }
            )
        subscriptions.add(subscription)
    }

    override fun addUser(user: User) {
        val subscription = userRepository
            .insert(user)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    addDone.value = AddUserState.Success
                },
                {
                    addDone.value = AddUserState.Error("Error happened while adding movie")
                    Timber.e(it)
                }
            )
        subscriptions.add(subscription)
    }

    override fun onCleared() {
        super.onCleared()
        subscriptions.dispose()
    }

}