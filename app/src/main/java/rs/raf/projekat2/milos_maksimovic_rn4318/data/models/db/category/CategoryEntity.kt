package rs.raf.projekat2.milos_maksimovic_rn4318.data.models.db.category

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "categories")
data class CategoryEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    @ColumnInfo(name = "image_url")
    val imageUrl: String,
    val title: String
)

