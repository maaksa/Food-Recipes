package rs.raf.projekat2.milos_maksimovic_rn4318.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import rs.raf.projekat2.milos_maksimovic_rn4318.data.models.resources.Resource
import rs.raf.projekat2.milos_maksimovic_rn4318.data.repositories.category.CategoryRepository
import rs.raf.projekat2.milos_maksimovic_rn4318.data.repositories.food.FoodRepository
import rs.raf.projekat2.milos_maksimovic_rn4318.presentation.contract.CategoryContract
import rs.raf.projekat2.milos_maksimovic_rn4318.presentation.contract.FoodContract
import rs.raf.projekat2.milos_maksimovic_rn4318.presentation.view.states.CategoryState
import rs.raf.projekat2.milos_maksimovic_rn4318.presentation.view.states.FoodState
import timber.log.Timber
import java.util.concurrent.TimeUnit

class FoodViewModel(
    private val foodRepository: FoodRepository,
) : ViewModel(), FoodContract.ViewModel {

    private val subscriptions = CompositeDisposable()
    override val foodState: MutableLiveData<FoodState> = MutableLiveData()

    private val publishSubject: PublishSubject<String> = PublishSubject.create()

    init {
        val subscription = publishSubject
            .debounce(200, TimeUnit.MILLISECONDS)
            .distinctUntilChanged()
            .switchMap {
                foodRepository
                    .getAll(it)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .doOnError {
                        Timber.e("Error in food by title method")
                        Timber.e(it)
                    }
            }
            .subscribe(
                {
                    foodState.value = FoodState.Success(it)
                },
                {
                    foodState.value =
                        FoodState.Error("Error happened while fetching data from db")
                    Timber.e(it)
                }
            )
        subscriptions.add(subscription)
    }


    override fun fetchAllFoods(querySearch: String, page: Int) {
        val subscription = foodRepository
            .fetchAll(querySearch, page)
            .startWith(Resource.Loading())
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    when (it) {
                        is Resource.Loading -> foodState.value = FoodState.Loading
                        is Resource.Success -> foodState.value = FoodState.DataFetched
                        is Resource.Error -> foodState.value =
                            FoodState.Error("Error happened while fetching data from the server")
                    }
                },
                {
                    foodState.value =
                        FoodState.Error("Error happened while fetching data from the server")
                    Timber.e(it)
                }
            )
        subscriptions.add(subscription)
    }

    override fun getAllByName(title: String) {
        publishSubject.onNext(title)
    }

}