package rs.raf.projekat2.milos_maksimovic_rn4318.data.datasources.local.food

import androidx.room.*
import io.reactivex.Completable
import io.reactivex.Observable
import rs.raf.projekat2.milos_maksimovic_rn4318.data.models.db.category.CategoryEntity
import rs.raf.projekat2.milos_maksimovic_rn4318.data.models.db.food.FoodEntity
import rs.raf.projekat2.milos_maksimovic_rn4318.data.models.db.food.FoodRecipeEntity
import rs.raf.projekat2.milos_maksimovic_rn4318.data.models.db.food.FoodWithRecipe

@Dao
abstract class FoodDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insert(entity: FoodEntity): Completable

    @Query("SELECT * FROM foods")
    abstract fun getAll(): Observable<List<FoodEntity>>

    @Query("SELECT * FROM foods WHERE saved == 1")
    abstract fun getAllSaved(): Observable<List<FoodEntity>>

    @Query("DELETE FROM foods WHERE saved == 0")
    abstract fun deleteAll()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertAll(entities: List<FoodEntity>): Completable

    @Query("SELECT * FROM foods WHERE title LIKE :title || '%'")
    abstract fun getByName(title: String): Observable<List<FoodEntity>>

    @Transaction
    open fun deleteAndInsertAll(entities: List<FoodEntity>) {
        deleteAll()
        insertAll(entities).blockingAwait()
    }

}