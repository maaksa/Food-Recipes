package rs.raf.projekat2.milos_maksimovic_rn4318.presentation.view.activities

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import org.koin.androidx.viewmodel.ext.android.viewModel
import rs.raf.projekat2.milos_maksimovic_rn4318.R
import rs.raf.projekat2.milos_maksimovic_rn4318.data.models.ui.Food
import rs.raf.projekat2.milos_maksimovic_rn4318.data.models.ui.FoodCategory
import rs.raf.projekat2.milos_maksimovic_rn4318.databinding.ActivityMainBinding
import rs.raf.projekat2.milos_maksimovic_rn4318.presentation.contract.CategoryContract
import rs.raf.projekat2.milos_maksimovic_rn4318.presentation.contract.FoodContract
import rs.raf.projekat2.milos_maksimovic_rn4318.presentation.view.recycler.adapter.CategoryAdapter
import rs.raf.projekat2.milos_maksimovic_rn4318.presentation.view.recycler.adapter.FoodAdapter
import rs.raf.projekat2.milos_maksimovic_rn4318.presentation.view.recycler.adapter.SavedFoodAdapter
import rs.raf.projekat2.milos_maksimovic_rn4318.presentation.view.states.CategoryState
import rs.raf.projekat2.milos_maksimovic_rn4318.presentation.view.states.FoodState
import rs.raf.projekat2.milos_maksimovic_rn4318.presentation.viewmodel.CategoryViewModel
import rs.raf.projekat2.milos_maksimovic_rn4318.presentation.viewmodel.FoodViewModel
import timber.log.Timber

class MainActivity : AppCompatActivity(), CategoryAdapter.OnCategoryItemClickListener,
    FoodAdapter.OnFoodItemClickListener {

    private lateinit var binding: ActivityMainBinding

    private val categoryViewModel: CategoryContract.ViewModel by viewModel<CategoryViewModel>()
    private val foodByNameViewModel: FoodContract.ViewModel by viewModel<FoodViewModel>()

    private lateinit var categoryAdapter: CategoryAdapter
    private lateinit var foodByNameAdapter: FoodAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        init()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.app_bar_menu, menu)
        val manager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchItem = menu?.findItem(R.id.miSearch)
        val searchView = searchItem?.actionView as SearchView

        searchView.setSearchableInfo(manager.getSearchableInfo(componentName))

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                searchView.clearFocus()
                searchView.setQuery("", false)
                searchItem.collapseActionView()

                if (query != null) {
                    if (binding.foodByNameListRv.visibility == View.VISIBLE) {

                    } else {
                        showHide(binding.categoryListRv)
                        showHide(binding.foodByNameListRv)
                    }
                    foodByNameViewModel.fetchAllFoods(query, 1)
                    foodByNameViewModel.getAllByName(query)
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.miCategories -> {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            }
            R.id.miSavedMenus -> {
                val intent = Intent(this, SavedFoodActivity::class.java)
                startActivity(intent)
                finish()
                foodByNameViewModel.getAllSaved()
            }
        }
        return true
    }

    private fun init() {
        initUi()
        initObservers()
    }

    private fun initUi() {
        initRecycler()
    }

    private fun initRecycler() {
        binding.categoryListRv.layoutManager = LinearLayoutManager(this)
        categoryAdapter = CategoryAdapter(this, Glide.with(this));
        binding.categoryListRv.adapter = categoryAdapter

        binding.foodByNameListRv.layoutManager = LinearLayoutManager(this)
        foodByNameAdapter = FoodAdapter(this, Glide.with(this));
        binding.foodByNameListRv.adapter = foodByNameAdapter

        if (binding.categoryListRv.visibility == View.VISIBLE) {
            if (binding.foodByNameListRv.visibility == View.VISIBLE) {
                showHide(binding.foodByNameListRv)
            }
        } else {
            showHide(binding.categoryListRv)
            if (binding.foodByNameListRv.visibility == View.VISIBLE) {
                showHide(binding.foodByNameListRv)
            }
        }

    }

    private fun initObservers() {
        categoryViewModel.categoryState.observe(this, Observer {
            renderStateCategory(it)
        })

        foodByNameViewModel.foodState.observe(this, Observer {
            renderStateFood(it)
        })

        categoryViewModel.getAllCategories()

        categoryViewModel.fetchAllCategories()
    }

    private fun renderStateCategory(state: CategoryState) {
        when (state) {
            is CategoryState.Success -> {
                showLoadingStateCategory(false)
                categoryAdapter.submitList(state.categories)
            }
            is CategoryState.Error -> {
                showLoadingStateCategory(false)
                Toast.makeText(this, state.message, Toast.LENGTH_SHORT).show()
            }
            is CategoryState.DataFetched -> {
                showLoadingStateCategory(false)
//                Toast.makeText(this, "Fresh data fetched from the server", Toast.LENGTH_LONG)
//                    .show()
            }
            is CategoryState.Loading -> {
                showLoadingStateCategory(true)
            }
        }
    }

    private fun renderStateFood(state: FoodState) {
        when (state) {
            is FoodState.Success -> {
                showLoadingStateFood(false)
                foodByNameAdapter.submitList(state.foods)
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

    private fun showLoadingStateFood(loading: Boolean) {
        binding.foodByNameListRv.isVisible = !loading
        binding.loadingPb.isVisible = loading
    }

    private fun showLoadingStateCategory(loading: Boolean) {
        binding.categoryListRv.isVisible = !loading
        binding.loadingPb.isVisible = loading
    }

    fun showHide(view: View) {
        view.visibility = if (view.visibility == View.VISIBLE) {
            View.GONE
        } else {
            View.VISIBLE
        }
    }

    override fun onItemClick(item: FoodCategory, position: Int) {
        if (binding.foodByNameListRv.visibility == View.VISIBLE) {
            if (binding.categoryListRv.visibility == View.VISIBLE) {
                showHide(binding.categoryListRv)
            }
        } else {
            showHide(binding.foodByNameListRv)
            if (binding.categoryListRv.visibility == View.VISIBLE) {
                showHide(binding.categoryListRv)
            }
        }
        foodByNameViewModel.fetchAllFoods(item.title, 1)
        foodByNameViewModel.getAllByName(item.title)
    }

    override fun onItemClick(item: Food, position: Int) {
        val intent = Intent(this, SingleFoodActivity::class.java)
        intent.putExtra("recipe_id", item.id)
        startActivity(intent)
    }

}