package rs.raf.projekat2.milos_maksimovic_rn4318.data.repositories.recipe

import io.reactivex.Completable
import io.reactivex.Observable
import rs.raf.projekat2.milos_maksimovic_rn4318.data.datasources.local.food.FoodDao
import rs.raf.projekat2.milos_maksimovic_rn4318.data.datasources.local.food.FoodRecipeDao
import rs.raf.projekat2.milos_maksimovic_rn4318.data.datasources.remote.FoodRecipeService
import rs.raf.projekat2.milos_maksimovic_rn4318.data.datasources.remote.FoodService
import rs.raf.projekat2.milos_maksimovic_rn4318.data.models.db.food.FoodEntity
import rs.raf.projekat2.milos_maksimovic_rn4318.data.models.db.food.FoodRecipeEntity
import rs.raf.projekat2.milos_maksimovic_rn4318.data.models.resources.Resource
import rs.raf.projekat2.milos_maksimovic_rn4318.data.models.ui.FoodRecipe
import rs.raf.projekat2.milos_maksimovic_rn4318.data.repositories.food.FoodRepository

class RecipeRepositoryImpl(
    private val localDataSource: FoodRecipeDao,
    private val remoteDataSource: FoodRecipeService
) : RecipeRepository {

    override fun fetchRecipeById(id: String): Observable<Resource<Unit>> {
        return remoteDataSource
            .getRecipeById(id)
            .doOnNext {
                val ingr: List<String> = it.recipe.ingredients
                val recipeStr: String = ""
                for (i in ingr) {
                    recipeStr.plus(",").plus(i)
                }
                val entitie =
                    FoodRecipeEntity(
                        0,
                        it.recipe.recipe_id,
                        it.recipe.title,
                        it.recipe.image_url,
                        it.recipe.social_rank,
                        it.recipe.ingredients.toString()
                    )
                localDataSource.deleteAndInsertRecipe(entitie)
            }
            .map {
                Resource.Success(Unit)
            }
    }

    override fun getRecipeById(id: String): Observable<FoodRecipe> {
        return localDataSource.getRecipeById(id).map {
            it.foodRecipeEntity?.let { it1 ->
                FoodRecipe(
                    it1.id,
                    it1.foodId,
                    it1.category_name,
                    it1.category_img,
                    it1.score,
                    it1.recipe
                )
            }
        }
    }

    override fun insert(entity: FoodRecipe): Completable {
        val foodRecipeEntity = FoodRecipeEntity(
            0,
            entity.foodid,
            entity.categoryName,
            entity.categoryImgUrl,
            entity.score,
            entity.ingredients
        )
        return localDataSource.insert(foodRecipeEntity)
    }

}