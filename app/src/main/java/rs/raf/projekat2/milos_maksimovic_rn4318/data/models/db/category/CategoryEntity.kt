package rs.raf.projekat2.milos_maksimovic_rn4318.data.models.db.category

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "categories")
data class CategoryEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Long,
    @ColumnInfo(name = "image_url")
    var imageUrl: String,
    var title: String
)

