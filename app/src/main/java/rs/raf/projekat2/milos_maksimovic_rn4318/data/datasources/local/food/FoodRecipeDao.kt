package rs.raf.projekat2.milos_maksimovic_rn4318.data.datasources.local.food

import androidx.room.*
import io.reactivex.Completable
import io.reactivex.Observable
import rs.raf.projekat2.milos_maksimovic_rn4318.data.models.db.food.FoodEntity
import rs.raf.projekat2.milos_maksimovic_rn4318.data.models.db.food.FoodRecipeEntity
import rs.raf.projekat2.milos_maksimovic_rn4318.data.models.db.food.FoodWithRecipe

@Dao
abstract class FoodRecipeDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertRecipe(entitie: FoodRecipeEntity): Completable

    @Query("DELETE FROM recipes")
    abstract fun deleteRecipe()

    @Transaction
    open fun deleteAndInsertRecipe(entitie: FoodRecipeEntity) {
        deleteRecipe()
        insertRecipe(entitie).blockingAwait()
    }

    @Transaction
    @Query("SELECT * FROM foods WHERE id LIKE :id")
    abstract fun getRecipeById(id: String): Observable<FoodWithRecipe>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insert(entity: FoodRecipeEntity): Completable

}