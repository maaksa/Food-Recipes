package rs.raf.projekat2.milos_maksimovic_rn4318.data.datasources.local.food

import androidx.room.*
import io.reactivex.Completable
import io.reactivex.Observable
import rs.raf.projekat2.milos_maksimovic_rn4318.data.models.db.food.FoodEntity
import rs.raf.projekat2.milos_maksimovic_rn4318.data.models.db.food.FoodWithRecipe

@Dao
abstract class FoodDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insert(entity: FoodEntity): Completable

    @Query("SELECT * FROM foods")
    abstract fun getAll(): Observable<List<FoodEntity>>

    @Query("SELECT * FROM foods WHERE saved == 1")
    abstract fun getAllSaved(): Observable<List<FoodEntity>>

    @Transaction
    @Query("SELECT * FROM foods WHERE id LIKE :id")
    abstract fun getById(id: String): Observable<FoodWithRecipe>

    //puca
//    @Transaction
//    @Query("SELECT * FROM recipes WHERE food_id == :foodId")
//    abstract fun getFoodWithRecipeById(foodId: Long): Observable<List<FoodWithRecipe>>
}