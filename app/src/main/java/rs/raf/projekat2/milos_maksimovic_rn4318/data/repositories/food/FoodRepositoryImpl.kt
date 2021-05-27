package rs.raf.projekat2.milos_maksimovic_rn4318.data.repositories.food

import io.reactivex.Completable
import io.reactivex.Observable
import rs.raf.projekat2.milos_maksimovic_rn4318.data.datasources.local.food.FoodDao
import rs.raf.projekat2.milos_maksimovic_rn4318.data.datasources.remote.FoodService
import rs.raf.projekat2.milos_maksimovic_rn4318.data.models.db.category.CategoryEntity
import rs.raf.projekat2.milos_maksimovic_rn4318.data.models.db.food.FoodEntity
import rs.raf.projekat2.milos_maksimovic_rn4318.data.models.resources.Resource
import rs.raf.projekat2.milos_maksimovic_rn4318.data.models.ui.Food
import rs.raf.projekat2.milos_maksimovic_rn4318.data.models.ui.FoodCategory
import timber.log.Timber
import java.util.*

class FoodRepositoryImpl(
    private val localDataSource: FoodDao,
    private val remoteDataSource: FoodService
) : FoodRepository {

    override fun fetchAll(querySearch: String, page: Int): Observable<Resource<Unit>> {
        return remoteDataSource
            .getAll(querySearch)
            .doOnNext {
                val entities = it.recipes.map {
                    FoodEntity(
                        0,
                        it.id,
                        it.image_url,
                        Date(),
                        querySearch,
                        it.social_rank,
                        it.title,
                        it.publisher,
                        0
                    )
                }
                localDataSource.deleteAndInsertAll(entities)
            }
            .map {
                Resource.Success(Unit)
            }
    }

    override fun getAll(title: String): Observable<List<Food>> {
        return localDataSource
            .getByName(title)
            .map {
                it.map {
                    Food(
                        it.id,
                        it.imageURL,
                        it.date,
                        it.categoryName,
                        it.title,
                        it.publisher,
                        it.socialRank
                    )
                }
            }
    }

    override fun fetchById(): Observable<Resource<Unit>> {
        TODO("Not yet implemented")
    }

    override fun getAllSaved(): Observable<List<Food>> {
        TODO("Not yet implemented")
    }

    override fun insert(entity: Food): Completable {
        TODO("Not yet implemented")
    }
}
