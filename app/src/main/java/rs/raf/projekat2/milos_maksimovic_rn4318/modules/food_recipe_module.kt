package rs.raf.projekat2.milos_maksimovic_rn4318.modules

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import rs.raf.projekat2.milos_maksimovic_rn4318.data.datasources.local.FoodDatabase
import rs.raf.projekat2.milos_maksimovic_rn4318.data.datasources.remote.FoodRecipeService
import rs.raf.projekat2.milos_maksimovic_rn4318.data.datasources.remote.FoodService
import rs.raf.projekat2.milos_maksimovic_rn4318.data.repositories.food.FoodRepository
import rs.raf.projekat2.milos_maksimovic_rn4318.data.repositories.food.FoodRepositoryImpl
import rs.raf.projekat2.milos_maksimovic_rn4318.data.repositories.recipe.RecipeRepository
import rs.raf.projekat2.milos_maksimovic_rn4318.data.repositories.recipe.RecipeRepositoryImpl
import rs.raf.projekat2.milos_maksimovic_rn4318.presentation.viewmodel.CategoryViewModel
import rs.raf.projekat2.milos_maksimovic_rn4318.presentation.viewmodel.FoodRecipesViewModel
import rs.raf.projekat2.milos_maksimovic_rn4318.presentation.viewmodel.FoodViewModel

val foodRecipeModule = module {

    viewModel { FoodRecipesViewModel(foodRecipesRepository = get()) }

    single<RecipeRepository> {
        RecipeRepositoryImpl(
            localDataSource = get(),
            remoteDataSource = get()
        )
    }

    single {
        get<FoodDatabase>().getFoodRecipeDao()
    }

    single<FoodRecipeService> {
        create(retrofit = get())
    }

}
