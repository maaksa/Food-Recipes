package rs.raf.projekat2.milos_maksimovic_rn4318.data.models.db.food

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "recipes")
class FoodRecipeEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val foodId: Long,
    val recipe: String
)