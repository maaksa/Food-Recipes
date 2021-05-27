package rs.raf.projekat2.milos_maksimovic_rn4318.data.repositories.category

import androidx.room.Query
import io.reactivex.Observable
import retrofit2.http.GET
import rs.raf.projekat2.milos_maksimovic_rn4318.data.models.resources.Resource
import rs.raf.projekat2.milos_maksimovic_rn4318.data.models.ui.FoodCategory

interface CategoryRepository {

    fun fetchAll(): Observable<Resource<Unit>>

    fun getAll(): Observable<List<FoodCategory>>

}