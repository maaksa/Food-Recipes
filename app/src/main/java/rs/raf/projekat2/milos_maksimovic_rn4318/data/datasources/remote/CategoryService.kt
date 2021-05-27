package rs.raf.projekat2.milos_maksimovic_rn4318.data.datasources.remote

import io.reactivex.Observable
import retrofit2.http.GET
import rs.raf.projekat2.milos_maksimovic_rn4318.data.models.api.cateogry.CategoriesResponse


interface CategoryService {

    @GET("v2/categories")
    fun getAll(): Observable<CategoriesResponse>

}