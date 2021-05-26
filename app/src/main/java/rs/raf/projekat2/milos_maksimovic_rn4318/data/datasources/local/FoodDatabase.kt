package rs.raf.projekat2.milos_maksimovic_rn4318.data.datasources.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import rs.raf.projekat2.milos_maksimovic_rn4318.data.datasources.local.converters.DateConverter
import rs.raf.projekat2.milos_maksimovic_rn4318.data.models.db.FoodEntity

@Database(
    entities = [FoodEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(DateConverter::class)
abstract class FoodDatabase : RoomDatabase() {
    abstract fun getFoodDao(): FoodDao

}