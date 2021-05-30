package rs.raf.projekat2.milos_maksimovic_rn4318.presentation.view.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import org.koin.androidx.viewmodel.ext.android.viewModel
import rs.raf.projekat2.milos_maksimovic_rn4318.R
import rs.raf.projekat2.milos_maksimovic_rn4318.data.models.ui.Food
import rs.raf.projekat2.milos_maksimovic_rn4318.databinding.ActivityLoginBinding
import rs.raf.projekat2.milos_maksimovic_rn4318.databinding.ActivitySavedFoodBinding
import rs.raf.projekat2.milos_maksimovic_rn4318.presentation.contract.FoodContract
import rs.raf.projekat2.milos_maksimovic_rn4318.presentation.view.recycler.adapter.CategoryAdapter
import rs.raf.projekat2.milos_maksimovic_rn4318.presentation.view.recycler.adapter.FoodAdapter
import rs.raf.projekat2.milos_maksimovic_rn4318.presentation.view.recycler.adapter.SavedFoodAdapter
import rs.raf.projekat2.milos_maksimovic_rn4318.presentation.view.states.FoodState
import rs.raf.projekat2.milos_maksimovic_rn4318.presentation.viewmodel.FoodViewModel

class SavedFoodActivity : AppCompatActivity(), SavedFoodAdapter.OnSavedFoodItemClickListener {

    private lateinit var binding: ActivitySavedFoodBinding

    private val savedFoodViewModel: FoodContract.ViewModel by viewModel<FoodViewModel>()

    private lateinit var savedFoodAdapter: SavedFoodAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySavedFoodBinding.inflate(layoutInflater)
        setContentView(binding.root)

        init()
    }

    private fun init() {
        initUi()
        initObservers()
    }

    private fun initUi() {
        initRecycler()
    }

    private fun initRecycler() {
        binding.savedFoodRv.layoutManager = LinearLayoutManager(this)
        savedFoodAdapter = SavedFoodAdapter(this, Glide.with(this));
        binding.savedFoodRv.adapter = savedFoodAdapter

    }

    private fun initObservers() {
        savedFoodViewModel.foodState.observe(this, Observer {
            renderStateFood(it)
        })

        savedFoodViewModel.getAllSaved()
    }

    private fun renderStateFood(state: FoodState) {
        when (state) {
            is FoodState.Success -> {
                showLoadingStateFood(false)
                savedFoodAdapter.submitList(state.foods)
            }
            is FoodState.Error -> {
                showLoadingStateFood(false)
                Toast.makeText(this, state.message, Toast.LENGTH_SHORT).show()
            }
            is FoodState.DataFetched -> {
                showLoadingStateFood(false)
//                Toast.makeText(this, "Fresh data fetched from the server", Toast.LENGTH_LONG)
//                    .show()
            }
            is FoodState.Loading -> {
                showLoadingStateFood(true)
            }
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    private fun showLoadingStateFood(loading: Boolean) {
        binding.savedFoodRv.isVisible = !loading
        binding.loadingPb.isVisible = loading
    }

    override fun onItemClick(item: Food, position: Int, title: String) {
        val intent = Intent(this, SingleFoodActivity::class.java)
        intent.putExtra("recipe_id", item.id)
        startActivity(intent)
    }
}