package rs.raf.projekat2.milos_maksimovic_rn4318.presentation.view.recycler.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.bumptech.glide.RequestManager
import rs.raf.projekat2.milos_maksimovic_rn4318.data.models.ui.Food
import rs.raf.projekat2.milos_maksimovic_rn4318.data.models.ui.FoodCategory
import rs.raf.projekat2.milos_maksimovic_rn4318.databinding.LayoutItemCategoryBinding
import rs.raf.projekat2.milos_maksimovic_rn4318.databinding.LayoutItemFoodByTitleBinding
import rs.raf.projekat2.milos_maksimovic_rn4318.presentation.view.recycler.diff.CategoryDiffCallback
import rs.raf.projekat2.milos_maksimovic_rn4318.presentation.view.recycler.diff.FoodDiffCallback
import rs.raf.projekat2.milos_maksimovic_rn4318.presentation.view.recycler.viewholder.CategoryViewHolder
import rs.raf.projekat2.milos_maksimovic_rn4318.presentation.view.recycler.viewholder.FoodViewHolder

class FoodAdapter(
    private val clickListener: OnCategoryItemClickListener,
    private val glide: RequestManager
) : ListAdapter<Food, FoodViewHolder>(FoodDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodViewHolder {
        val itemBinding =
            LayoutItemFoodByTitleBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FoodViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: FoodViewHolder, position: Int) {
        val singleFood = getItem(position)
        holder.bind(singleFood, glide)
        holder.init(singleFood, clickListener)
    }

    interface OnCategoryItemClickListener {
        fun onItemClick(item: Food, position: Int)
    }

}