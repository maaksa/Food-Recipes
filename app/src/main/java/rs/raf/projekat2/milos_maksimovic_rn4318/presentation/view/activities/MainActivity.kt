package rs.raf.projekat2.milos_maksimovic_rn4318.presentation.view.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import org.koin.androidx.viewmodel.ext.android.viewModel
import rs.raf.projekat2.milos_maksimovic_rn4318.data.models.ui.FoodCategory
import rs.raf.projekat2.milos_maksimovic_rn4318.databinding.ActivityMainBinding
import rs.raf.projekat2.milos_maksimovic_rn4318.presentation.contract.CategoryContract
import rs.raf.projekat2.milos_maksimovic_rn4318.presentation.view.recycler.adapter.CategoryAdapter
import rs.raf.projekat2.milos_maksimovic_rn4318.presentation.view.states.CategoryState
import rs.raf.projekat2.milos_maksimovic_rn4318.presentation.viewmodel.CategoryViewModel
import timber.log.Timber

class MainActivity : AppCompatActivity(), CategoryAdapter.OnCategoryItemClickListener {

    private lateinit var binding: ActivityMainBinding

    private val mainViewModel: CategoryContract.ViewModel by viewModel<CategoryViewModel>()
    private lateinit var adapter: CategoryAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
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
        binding.categoryListRv.layoutManager = LinearLayoutManager(this)
        adapter = CategoryAdapter(this, Glide.with(this));
        binding.categoryListRv.adapter = adapter
    }

    private fun initObservers() {
        mainViewModel.categoryState.observe(this, Observer {
            renderState(it)
        })

        mainViewModel.getAllCategories()

        mainViewModel.fetchAllCategories()
    }

    override fun onItemClick(item: FoodCategory, position: Int) {
        TODO("Not yet implemented")
    }

    private fun renderState(state: CategoryState) {
        when (state) {
            is CategoryState.Success -> {
                showLoadingState(false)
                adapter.submitList(state.categories)
            }
            is CategoryState.Error -> {
                showLoadingState(false)
                Toast.makeText(this, state.message, Toast.LENGTH_SHORT).show()
            }
            is CategoryState.DataFetched -> {
                showLoadingState(false)
                Toast.makeText(this, "Fresh data fetched from the server", Toast.LENGTH_LONG)
                    .show()
            }
            is CategoryState.Loading -> {
                showLoadingState(true)
            }
        }
    }

    private fun showLoadingState(loading: Boolean) {
        //binding.inputEt.isVisible = !loading
        binding.categoryListRv.isVisible = !loading
        binding.loadingPb.isVisible = loading
    }

}