package rs.raf.projekat2.milos_maksimovic_rn4318.modules

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import rs.raf.projekat2.milos_maksimovic_rn4318.data.datasources.local.FoodDatabase
import rs.raf.projekat2.milos_maksimovic_rn4318.data.datasources.remote.CategoryService
import rs.raf.projekat2.milos_maksimovic_rn4318.data.datasources.remote.FoodService
import rs.raf.projekat2.milos_maksimovic_rn4318.data.repositories.category.CategoryRepository
import rs.raf.projekat2.milos_maksimovic_rn4318.data.repositories.category.CategoryRepositoryImpl
import rs.raf.projekat2.milos_maksimovic_rn4318.data.repositories.food.FoodRepository
import rs.raf.projekat2.milos_maksimovic_rn4318.data.repositories.food.FoodRepositoryImpl
import rs.raf.projekat2.milos_maksimovic_rn4318.presentation.viewmodel.CategoryViewModel

val categoryModule = module {

    viewModel { CategoryViewModel(categoryRepository = get()) }

    single<CategoryRepository> {
        CategoryRepositoryImpl(
            localDataSource = get(),
            remoteDataSource = get()
        )
    }

    single {
        get<FoodDatabase>().getCategoryDao()
    }

    single<CategoryService> {
        create(retrofit = get())
    }

}