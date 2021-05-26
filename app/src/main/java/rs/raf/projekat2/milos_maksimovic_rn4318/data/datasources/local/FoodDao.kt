package rs.raf.projekat2.milos_maksimovic_rn4318.data.datasources.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.Completable
import io.reactivex.Observable
import rs.raf.projekat2.milos_maksimovic_rn4318.data.models.db.FoodEntity

@Dao
abstract class FoodDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insert(entity: FoodEntity): Completable

    @Query("SELECT * FROM foods")
    abstract fun getAll(): Observable<List<FoodEntity>>

    @Query("SELECT * FROM foods WHERE id == :id")
    abstract fun getById(id: Long): FoodEntity
}