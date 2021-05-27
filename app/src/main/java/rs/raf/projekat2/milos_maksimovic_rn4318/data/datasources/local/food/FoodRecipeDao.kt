package rs.raf.projekat2.milos_maksimovic_rn4318.data.datasources.local.food

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import io.reactivex.Completable
import rs.raf.projekat2.milos_maksimovic_rn4318.data.models.db.food.FoodRecipeEntity

@Dao
abstract class FoodRecipeDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insert(entity: FoodRecipeEntity): Completable

}