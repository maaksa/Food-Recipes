package rs.raf.projekat2.milos_maksimovic_rn4318.presentation.view.recycler.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.squareup.picasso.Picasso
import rs.raf.projekat2.milos_maksimovic_rn4318.data.models.ui.Food
import rs.raf.projekat2.milos_maksimovic_rn4318.data.models.ui.FoodCategory
import rs.raf.projekat2.milos_maksimovic_rn4318.databinding.LayoutItemCategoryBinding
import rs.raf.projekat2.milos_maksimovic_rn4318.databinding.LayoutItemFoodByTitleBinding
import rs.raf.projekat2.milos_maksimovic_rn4318.presentation.view.recycler.adapter.CategoryAdapter
import rs.raf.projekat2.milos_maksimovic_rn4318.presentation.view.recycler.adapter.FoodAdapter
import kotlin.math.roundToInt

class FoodViewHolder(private val itemBinding: LayoutItemFoodByTitleBinding) :
    RecyclerView.ViewHolder(itemBinding.root) {

    fun init(item: Food, action: FoodAdapter.OnCategoryItemClickListener) {
        itemBinding.foodByNameItemCl.setOnClickListener {
            action.onItemClick(item, adapterPosition)
        }
    }

    fun bind(food: Food, glide: RequestManager) {
        itemBinding.scoreTv.text = food.score.roundToInt().toString()
        itemBinding.titleTv.text = food.foodName
        itemBinding.publisherTv.text = food.publisher
        glide.load(food.imageURL).into(itemBinding.foodByNameImgIv)
    }

}