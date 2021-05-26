package rs.raf.projekat2.milos_maksimovic_rn4318.data.models.db.food

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "foods")
data class FoodEntity(
    @PrimaryKey(autoGenerate = true)
    val uid: Long,
    var id: String,
    @ColumnInfo(name = "image_url")
    val imageURL: String,
    @ColumnInfo(name = "date")
    var date: Date = Date(),
    @ColumnInfo(name = "category_name")
    var categoryName: String,
    @ColumnInfo(name = "social_rank")
    var socialRank: String,
    var title: String,
    var publisher: String,
    val saved: Int
)

