package rs.raf.projekat2.milos_maksimovic_rn4318.modules

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import rs.raf.projekat2.milos_maksimovic_rn4318.data.datasources.local.FoodDatabase
import rs.raf.projekat2.milos_maksimovic_rn4318.data.datasources.remote.FoodService
import rs.raf.projekat2.milos_maksimovic_rn4318.data.repositories.food.FoodRepository
import rs.raf.projekat2.milos_maksimovic_rn4318.data.repositories.food.FoodRepositoryImpl
import rs.raf.projekat2.milos_maksimovic_rn4318.presentation.viewmodel.CategoryViewModel
import rs.raf.projekat2.milos_maksimovic_rn4318.presentation.viewmodel.FoodViewModel

val foodModule = module {

    viewModel { FoodViewModel(foodRepository = get()) }

    single<FoodRepository> {
        FoodRepositoryImpl(
            localDataSource = get(),
            remoteDataSource = get()
        )
    }

    single {
        get<FoodDatabase>().getFoodDao()
    }

    single<FoodService> {
        create(retrofit = get())
    }

}
