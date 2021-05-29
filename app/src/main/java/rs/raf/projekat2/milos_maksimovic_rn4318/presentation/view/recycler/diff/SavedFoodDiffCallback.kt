package rs.raf.projekat2.milos_maksimovic_rn4318.presentation.view.recycler.diff

import androidx.recyclerview.widget.DiffUtil
import rs.raf.projekat2.milos_maksimovic_rn4318.data.models.ui.Food
import rs.raf.projekat2.milos_maksimovic_rn4318.data.models.ui.FoodCategory

class SavedFoodDiffCallback : DiffUtil.ItemCallback<Food>() {

    override fun areContentsTheSame(oldItem: Food, newItem: Food): Boolean {
        return oldItem.imageURL == newItem.imageURL && oldItem.foodName == newItem.foodName && oldItem.date == newItem.date
    }

    override fun areItemsTheSame(oldItem: Food, newItem: Food): Boolean {
        return oldItem.id == newItem.id
    }

}