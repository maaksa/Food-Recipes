package rs.raf.projekat2.milos_maksimovic_rn4318.presentation.view.recycler.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.squareup.picasso.Picasso
import rs.raf.projekat2.milos_maksimovic_rn4318.data.models.ui.FoodCategory
import rs.raf.projekat2.milos_maksimovic_rn4318.databinding.LayoutItemCategoryBinding
import rs.raf.projekat2.milos_maksimovic_rn4318.presentation.view.recycler.adapter.CategoryAdapter

class CategoryViewHolder(private val itemBinding: LayoutItemCategoryBinding) :
    RecyclerView.ViewHolder(itemBinding.root) {

    fun init(item: FoodCategory, action: CategoryAdapter.OnCategoryItemClickListener) {
        itemBinding.categoryItemCl.setOnClickListener {
            action.onItemClick(item, adapterPosition)
        }
    }

    fun bind(category: FoodCategory, glide: RequestManager) {
        itemBinding.titleTv.text = category.title
        glide.load(category.imageUrl).circleCrop().into(itemBinding.categoryImgIv)
        //Picasso.get().load(category.imageUrl).into(itemBinding.categoryImgIv)
    }

}