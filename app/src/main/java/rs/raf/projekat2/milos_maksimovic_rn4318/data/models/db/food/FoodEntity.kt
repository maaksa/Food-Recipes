package rs.raf.projekat2.milos_maksimovic_rn4318.data.models.db.food

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "foods")
data class FoodEntity(
    @PrimaryKey(autoGenerate = true)
    val uid: Long,
    val id: Int,
    @ColumnInfo(name = "image_url")
    val imageURL: String,
    @ColumnInfo(name = "date")
    val date: Date = Date(),
    @ColumnInfo(name = "category_name")
    val categoryName: String,
    @ColumnInfo(name = "social_rank")
    val socialRank: Double,
    val title: String,
    val publisher: String,
    val saved: Int
)

