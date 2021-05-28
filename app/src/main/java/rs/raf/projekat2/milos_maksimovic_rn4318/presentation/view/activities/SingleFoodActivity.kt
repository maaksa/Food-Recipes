package rs.raf.projekat2.milos_maksimovic_rn4318.presentation.view.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import org.koin.androidx.viewmodel.ext.android.viewModel
import rs.raf.projekat2.milos_maksimovic_rn4318.R
import rs.raf.projekat2.milos_maksimovic_rn4318.databinding.ActivityLoginBinding
import rs.raf.projekat2.milos_maksimovic_rn4318.databinding.ActivitySingleFoodBinding
import rs.raf.projekat2.milos_maksimovic_rn4318.presentation.contract.CategoryContract
import rs.raf.projekat2.milos_maksimovic_rn4318.presentation.contract.FoodContract
import rs.raf.projekat2.milos_maksimovic_rn4318.presentation.contract.FoodRecipesContract
import rs.raf.projekat2.milos_maksimovic_rn4318.presentation.view.states.CategoryState
import rs.raf.projekat2.milos_maksimovic_rn4318.presentation.view.states.FoodRecipesState
import rs.raf.projekat2.milos_maksimovic_rn4318.presentation.view.states.FoodState
import rs.raf.projekat2.milos_maksimovic_rn4318.presentation.viewmodel.CategoryViewModel
import rs.raf.projekat2.milos_maksimovic_rn4318.presentation.viewmodel.FoodRecipesViewModel
import rs.raf.projekat2.milos_maksimovic_rn4318.presentation.viewmodel.FoodViewModel

class SingleFoodActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySingleFoodBinding

    private val foodRecipeViewModel: FoodRecipesContract.ViewModel by viewModel<FoodRecipesViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySingleFoodBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val recipeId: String = intent.getStringExtra("recipe_id").toString()
        Glide.with(this).load(recipeId).into(binding.imgTv)

        init(recipeId)
    }

    private fun init(recipeId: String) {
        initUi(recipeId)
    }

    private fun initUi(recipeId: String) {
        initObservers(recipeId)
    }

    private fun initObservers(recipeId: String) {
        foodRecipeViewModel.foodRecipesState.observe(this, Observer {
            renderStateFood(it, recipeId)
        })

        foodRecipeViewModel.fetchRecipeById(recipeId)

    }

    private fun renderStateFood(state: FoodRecipesState, recipeId: String) {
        when (state) {
            is FoodRecipesState.Success -> {
                showLoadingStateFood(false)
                Glide.with(this).load(state.foodRecipe.categoryImgUrl).into(binding.imgTv)
            }
            is FoodRecipesState.Error -> {
                showLoadingStateFood(false)
                Toast.makeText(this, state.message, Toast.LENGTH_SHORT).show()
            }
            is FoodRecipesState.DataFetched -> {
                foodRecipeViewModel.getRecipeById(recipeId)
                showLoadingStateFood(false)
                Toast.makeText(this, "Fresh data fetched from the server", Toast.LENGTH_LONG)
                    .show()
            }
            is FoodRecipesState.Loading -> {
                showLoadingStateFood(true)
            }
        }

    }

    private fun showLoadingStateFood(loading: Boolean) {
        //binding.inputEt.isVisible = !loading
        //binding.foodByNameListRv.isVisible = !loading
        //binding.loadingPb.isVisible = loading
    }

}