package rs.raf.projekat2.milos_maksimovic_rn4318.presentation.view.recycler.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.bumptech.glide.RequestManager
import rs.raf.projekat2.milos_maksimovic_rn4318.data.models.ui.Food
import rs.raf.projekat2.milos_maksimovic_rn4318.data.models.ui.FoodCategory
import rs.raf.projekat2.milos_maksimovic_rn4318.databinding.LayoutItemCategoryBinding
import rs.raf.projekat2.milos_maksimovic_rn4318.databinding.LayoutItemFoodByTitleBinding
import rs.raf.projekat2.milos_maksimovic_rn4318.databinding.LayoutSavedFoodBinding
import rs.raf.projekat2.milos_maksimovic_rn4318.presentation.view.recycler.diff.CategoryDiffCallback
import rs.raf.projekat2.milos_maksimovic_rn4318.presentation.view.recycler.diff.FoodDiffCallback
import rs.raf.projekat2.milos_maksimovic_rn4318.presentation.view.recycler.diff.SavedFoodDiffCallback
import rs.raf.projekat2.milos_maksimovic_rn4318.presentation.view.recycler.viewholder.CategoryViewHolder
import rs.raf.projekat2.milos_maksimovic_rn4318.presentation.view.recycler.viewholder.FoodViewHolder
import rs.raf.projekat2.milos_maksimovic_rn4318.presentation.view.recycler.viewholder.SavedFoodViewHolder

class SavedFoodAdapter(
    private val clickListener: OnSavedFoodItemClickListener,
    private val glide: RequestManager
) : ListAdapter<Food, SavedFoodViewHolder>(SavedFoodDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SavedFoodViewHolder {
        val itemBinding =
            LayoutSavedFoodBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SavedFoodViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: SavedFoodViewHolder, position: Int) {
        val singleFood = getItem(position)
        holder.bind(singleFood, glide)
        holder.init(singleFood, clickListener)
    }

    interface OnSavedFoodItemClickListener {
        fun onItemClick(item: Food, position: Int, categoryName: String)
    }

}