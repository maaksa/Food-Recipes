package rs.raf.projekat2.milos_maksimovic_rn4318.data.models.db.food

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "recipes")
data class FoodRecipeEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    @ColumnInfo(name = "food_id")
    val foodId: String,
    val category_name: String,
    val category_img: String,
    val score: Double,
    val recipe: String
)
