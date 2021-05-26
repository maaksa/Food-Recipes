package rs.raf.projekat2.milos_maksimovic_rn4318.data.models.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "foods")
data class FoodEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Long,
    var name: String,
    @ColumnInfo(name = "date")
    var date: Date = Date(),
    @ColumnInfo(name = "recipe_id")
    val recipeId: Long,
    var category: String,
)

