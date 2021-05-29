package rs.raf.projekat2.milos_maksimovic_rn4318.presentation.view.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import org.koin.androidx.viewmodel.ext.android.viewModel
import rs.raf.projekat2.milos_maksimovic_rn4318.R
import rs.raf.projekat2.milos_maksimovic_rn4318.data.models.ui.FoodRecipe
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
    private lateinit var foodRecipieIntent: FoodRecipe

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
        initListeners()
    }

    private fun initListeners() {
        binding.saveRecipeBtn.setOnClickListener {
            val intent = Intent(this, SaveRecipeActivity::class.java)
            intent.putExtra("foodRecipe", foodRecipieIntent)
            startActivity(intent)
            //finish()
        }
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
                binding.scoreTv.text = state.foodRecipe.score.toInt().toString()
                binding.titleTv.text = state.foodRecipe.categoryName

                var str = state.foodRecipe.ingredients.replace("[", "").replace("]", "")
                var list = str.split(",")
                var new = ""

                for (l in list) {
                    new = new + l + "\n"
                }

                binding.textView.text = new

                foodRecipieIntent = FoodRecipe(
                    state.foodRecipe.id,
                    state.foodRecipe.foodid,
                    state.foodRecipe.categoryName,
                    state.foodRecipe.categoryImgUrl,
                    state.foodRecipe.score,
                    state.foodRecipe.ingredients
                )

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
        binding.loadingPb.isVisible = loading
    }

}