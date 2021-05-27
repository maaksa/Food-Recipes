package rs.raf.projekat2.milos_maksimovic_rn4318.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import rs.raf.projekat2.milos_maksimovic_rn4318.data.models.resources.Resource
import rs.raf.projekat2.milos_maksimovic_rn4318.data.repositories.category.CategoryRepository
import rs.raf.projekat2.milos_maksimovic_rn4318.presentation.contract.CategoryContract
import rs.raf.projekat2.milos_maksimovic_rn4318.presentation.view.states.CategoryState
import timber.log.Timber

class CategoryViewModel(
    private val categoryRepository: CategoryRepository,
) : ViewModel(), CategoryContract.ViewModel {

    private val subscriptions = CompositeDisposable()
    override val categoryState: MutableLiveData<CategoryState> = MutableLiveData()

    override fun fetchAllCategories() {
        val subscription = categoryRepository
            .fetchAll()
            .startWith(Resource.Loading())
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    when (it) {
                        is Resource.Loading -> categoryState.value = CategoryState.Loading
                        is Resource.Success -> categoryState.value = CategoryState.DataFetched
                        is Resource.Error -> categoryState.value =
                            CategoryState.Error("Error happened while fetching data from the server")
                    }
                },
                {
                    categoryState.value =
                        CategoryState.Error("Error happened while fetching data from the server")
                    Timber.e(it)
                }
            )
        subscriptions.add(subscription)
    }

    override fun getAllCategories() {
        val subscription = categoryRepository
            .getAll()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    categoryState.value = CategoryState.Success(it)
                },
                {
                    categoryState.value =
                        CategoryState.Error("Error happened while fetching data from db")
                    Timber.e(it)
                }
            )
        subscriptions.add(subscription)
    }

}