package rs.raf.projekat2.milos_maksimovic_rn4318.presentation.view.recycler.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.bumptech.glide.RequestManager
import rs.raf.projekat2.milos_maksimovic_rn4318.data.models.ui.FoodCategory
import rs.raf.projekat2.milos_maksimovic_rn4318.databinding.LayoutItemCategoryBinding
import rs.raf.projekat2.milos_maksimovic_rn4318.presentation.view.recycler.diff.CategoryDiffCallback
import rs.raf.projekat2.milos_maksimovic_rn4318.presentation.view.recycler.viewholder.CategoryViewHolder

class CategoryAdapter(
    private val clickListener: OnCategoryItemClickListener,
    private val glide: RequestManager
) : ListAdapter<FoodCategory, CategoryViewHolder>(CategoryDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val itemBinding =
            LayoutItemCategoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CategoryViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val singleCategory = getItem(position)
        holder.bind(singleCategory, glide)
        holder.init(singleCategory, clickListener)
    }

    interface OnCategoryItemClickListener {
        fun onItemClick(item: FoodCategory, position: Int)
    }

}