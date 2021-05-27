package rs.raf.projekat2.milos_maksimovic_rn4318.presentation.view.recycler.diff

import androidx.recyclerview.widget.DiffUtil
import rs.raf.projekat2.milos_maksimovic_rn4318.data.models.ui.FoodCategory

class CategoryDiffCallback : DiffUtil.ItemCallback<FoodCategory>() {


    override fun areContentsTheSame(oldItem: FoodCategory, newItem: FoodCategory): Boolean {
        return oldItem.title == newItem.title && oldItem.imageUrl == newItem.imageUrl
    }

    override fun areItemsTheSame(oldItem: FoodCategory, newItem: FoodCategory): Boolean {
        return oldItem.id == newItem.id
    }

}