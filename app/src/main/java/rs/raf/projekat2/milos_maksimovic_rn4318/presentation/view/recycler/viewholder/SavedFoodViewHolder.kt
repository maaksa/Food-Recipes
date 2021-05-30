package rs.raf.projekat2.milos_maksimovic_rn4318.presentation.view.recycler.viewholder

import android.R
import android.content.Context
import android.content.ContextWrapper
import android.graphics.BitmapFactory
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.squareup.picasso.Picasso
import rs.raf.projekat2.milos_maksimovic_rn4318.data.models.ui.Food
import rs.raf.projekat2.milos_maksimovic_rn4318.data.models.ui.FoodCategory
import rs.raf.projekat2.milos_maksimovic_rn4318.databinding.LayoutItemCategoryBinding
import rs.raf.projekat2.milos_maksimovic_rn4318.databinding.LayoutItemFoodByTitleBinding
import rs.raf.projekat2.milos_maksimovic_rn4318.databinding.LayoutSavedFoodBinding
import rs.raf.projekat2.milos_maksimovic_rn4318.presentation.view.recycler.adapter.CategoryAdapter
import rs.raf.projekat2.milos_maksimovic_rn4318.presentation.view.recycler.adapter.FoodAdapter
import rs.raf.projekat2.milos_maksimovic_rn4318.presentation.view.recycler.adapter.SavedFoodAdapter
import java.io.File
import java.io.FileInputStream
import java.io.FileNotFoundException
import kotlin.math.roundToInt

class SavedFoodViewHolder(private val itemBinding: LayoutSavedFoodBinding) :
    RecyclerView.ViewHolder(itemBinding.root) {

    fun init(item: Food, action: SavedFoodAdapter.OnSavedFoodItemClickListener) {
        itemBinding.savedFoodItemCl.setOnClickListener {
            action.onItemClick(item, adapterPosition, item.categoryName)
        }
    }

    fun bind(food: Food, glide: RequestManager) {
        itemBinding.titleTv.text = food.foodName
        itemBinding.dateTv.text = food.date
        itemBinding.categoryTv.text = food.categoryName

        if (food.imageURL.contains("data")) {
            try {
                val f = File(food.imageURL, "slika.jpg")
                val b = BitmapFactory.decodeStream(FileInputStream(f))
                glide.load(b).placeholder(R.drawable.ic_delete)
                    .into(itemBinding.foodByNameImgIv)
            } catch (e: FileNotFoundException) {
                e.printStackTrace()
            }
        } else {

            glide.load(food.imageURL).into(itemBinding.foodByNameImgIv)

        }
    }

}