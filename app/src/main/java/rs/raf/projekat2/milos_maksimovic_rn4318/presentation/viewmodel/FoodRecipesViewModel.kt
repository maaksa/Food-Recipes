package rs.raf.projekat2.milos_maksimovic_rn4318.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import rs.raf.projekat2.milos_maksimovic_rn4318.data.models.resources.Resource
import rs.raf.projekat2.milos_maksimovic_rn4318.data.models.ui.FoodRecipe
import rs.raf.projekat2.milos_maksimovic_rn4318.data.repositories.category.CategoryRepository
import rs.raf.projekat2.milos_maksimovic_rn4318.data.repositories.food.FoodRepository
import rs.raf.projekat2.milos_maksimovic_rn4318.data.repositories.recipe.RecipeRepository
import rs.raf.projekat2.milos_maksimovic_rn4318.presentation.contract.CategoryContract
import rs.raf.projekat2.milos_maksimovic_rn4318.presentation.contract.FoodContract
import rs.raf.projekat2.milos_maksimovic_rn4318.presentation.contract.FoodRecipesContract
import rs.raf.projekat2.milos_maksimovic_rn4318.presentation.view.states.*
import timber.log.Timber
import java.util.concurrent.TimeUnit

class FoodRecipesViewModel(
    private val foodRecipesRepository: RecipeRepository,
) : ViewModel(), FoodRecipesContract.ViewModel {

    private val subscriptions = CompositeDisposable()
    override val foodRecipesState: MutableLiveData<FoodRecipesState> = MutableLiveData()
    override val addDone: MutableLiveData<AddFoodRecipeState> = MutableLiveData()

    private val publishSubject: PublishSubject<String> = PublishSubject.create()


    init {
        val subscription2 = publishSubject
            .debounce(200, TimeUnit.MILLISECONDS)
            .distinctUntilChanged()
            .switchMap {
                foodRecipesRepository
                    .getRecipeById(it)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .doOnError {
                        Timber.e("Error in food recipe method find by id")
                        Timber.e(it)
                    }
            }
            .subscribe(
                {
                    Timber.e("OVO JE URL ${it.categoryImgUrl}")
                    foodRecipesState.value = FoodRecipesState.Success(it)
                },
                {
                    foodRecipesState.value =
                        FoodRecipesState.Error("Error happened while fetching data from db")
                    Timber.e(it)
                }
            )
        subscriptions.add(subscription2)
    }

    override fun fetchRecipeById(id: String) {
        val subscription = foodRecipesRepository
            .fetchRecipeById(id)
            .startWith(Resource.Loading())
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    when (it) {
                        is Resource.Loading -> foodRecipesState.value = FoodRecipesState.Loading
                        is Resource.Success -> foodRecipesState.value = FoodRecipesState.DataFetched
                        is Resource.Error -> foodRecipesState.value =
                            FoodRecipesState.Error("Error happened while fetching data from the server")
                    }
                },
                {
                    foodRecipesState.value =
                        FoodRecipesState.Error("Error happened while fetching data from the server")
                    Timber.e(it)
                }
            )
        subscriptions.add(subscription)
    }

    override fun getRecipeById(id: String) {
        publishSubject.onNext(id)
    }

    override fun addFoodRecipe(foodRecip: FoodRecipe) {
        val subscription = foodRecipesRepository
            .insert(foodRecip)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    addDone.value = AddFoodRecipeState.Success
                },
                {
                    addDone.value = AddFoodRecipeState.Error("Error happened while adding movie")
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