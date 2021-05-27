package rs.raf.projekat2.milos_maksimovic_rn4318.data.repositories.food

import io.reactivex.Completable
import io.reactivex.Observable

import rs.raf.projekat2.milos_maksimovic_rn4318.data.models.resources.Resource
import rs.raf.projekat2.milos_maksimovic_rn4318.data.models.ui.Food

interface FoodRepository {

    fun fetchAll(querySearch: String, page: Int): Observable<Resource<Unit>>

    fun fetchById(): Observable<Resource<Unit>>

    fun getAll(title: String): Observable<List<Food>>

    fun getAllSaved(): Observable<List<Food>>

    fun insert(entity: Food): Completable

//    @Transaction
//    @androidx.room.Query("SELECT * FROM foods WHERE id LIKE :id")
//    abstract fun getById(id: String): Observable<FoodWithRecipe>

}