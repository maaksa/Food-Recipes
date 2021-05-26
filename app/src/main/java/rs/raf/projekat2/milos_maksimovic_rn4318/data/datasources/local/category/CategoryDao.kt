package rs.raf.projekat2.milos_maksimovic_rn4318.data.datasources.local.category

import androidx.room.Dao
import androidx.room.Query
import io.reactivex.Observable
import rs.raf.projekat2.milos_maksimovic_rn4318.data.models.db.category.CategoryEntity

@Dao
abstract class CategoryDao {

    @Query("SELECT * FROM categories")
    abstract fun getAll(): Observable<List<CategoryEntity>>

}