package rs.raf.projekat2.milos_maksimovic_rn4318.data.repositories.category

import io.reactivex.Observable
import rs.raf.projekat2.milos_maksimovic_rn4318.data.datasources.local.category.CategoryDao
import rs.raf.projekat2.milos_maksimovic_rn4318.data.datasources.remote.CategoryService
import rs.raf.projekat2.milos_maksimovic_rn4318.data.models.db.category.CategoryEntity
import rs.raf.projekat2.milos_maksimovic_rn4318.data.models.resources.Resource
import rs.raf.projekat2.milos_maksimovic_rn4318.data.models.ui.FoodCategory
import timber.log.Timber

class CategoryRepositoryImpl(
    private val localDataSource: CategoryDao,
    private val remoteDataSource: CategoryService
) : CategoryRepository {

    override fun fetchAll(): Observable<Resource<Unit>> {
        return remoteDataSource
            .getAll()
            .doOnNext {
                Timber.e("Upis u bazu")
                val entities = it.categories.map {
                    CategoryEntity(
                        0,
                        it.imageUrl,
                        it.title
                    )
                }
                localDataSource.deleteAndInsertAll(entities)
            }
            .map {
                Resource.Success(Unit)
            }
    }

    override fun getAll(): Observable<List<FoodCategory>> {
        return localDataSource
            .getAll()
            .map {
                it.map {
                    FoodCategory(
                        it.id,
                        it.imageUrl,
                        it.title
                    )
                }
            }
    }

}