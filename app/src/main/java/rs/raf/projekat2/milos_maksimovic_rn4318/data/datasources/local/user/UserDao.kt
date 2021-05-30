package rs.raf.projekat2.milos_maksimovic_rn4318.data.datasources.local.user

import androidx.room.*
import io.reactivex.Completable
import io.reactivex.Observable
import rs.raf.projekat2.milos_maksimovic_rn4318.data.models.db.category.CategoryEntity
import rs.raf.projekat2.milos_maksimovic_rn4318.data.models.db.food.FoodEntity
import rs.raf.projekat2.milos_maksimovic_rn4318.data.models.db.food.FoodRecipeEntity
import rs.raf.projekat2.milos_maksimovic_rn4318.data.models.db.food.FoodWithRecipe
import rs.raf.projekat2.milos_maksimovic_rn4318.data.models.db.user.UserEntity
import rs.raf.projekat2.milos_maksimovic_rn4318.data.models.ui.User

@Dao
abstract class UserDao {


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insert(entity: UserEntity): Completable

    @Query("SELECT COUNT(id) FROM users")
    abstract fun getByPin(): Observable<Int>

    @Query("DELETE FROM users")
    abstract fun deleteAll()

    @Transaction
    open fun deleteAndInsert(entitie: UserEntity) {
        deleteAll()
        insert(entitie).blockingAwait()
    }

}