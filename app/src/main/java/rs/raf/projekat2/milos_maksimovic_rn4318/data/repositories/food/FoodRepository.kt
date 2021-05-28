package rs.raf.projekat2.milos_maksimovic_rn4318.data.repositories.food

import io.reactivex.Completable
import io.reactivex.Observable

import rs.raf.projekat2.milos_maksimovic_rn4318.data.models.resources.Resource
import rs.raf.projekat2.milos_maksimovic_rn4318.data.models.ui.Food
import rs.raf.projekat2.milos_maksimovic_rn4318.data.models.ui.FoodRecipe

interface FoodRepository {

    fun fetchAll(querySearch: String, page: Int): Observable<Resource<Unit>>

    fun getAll(title: String): Observable<List<Food>>

    fun getAllSaved(): Observable<List<Food>>

    fun insert(entity: Food): Completable

}