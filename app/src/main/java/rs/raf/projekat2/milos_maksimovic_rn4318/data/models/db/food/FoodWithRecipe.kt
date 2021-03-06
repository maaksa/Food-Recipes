package rs.raf.projekat2.milos_maksimovic_rn4318.data.models.db.food

import androidx.room.Embedded
import androidx.room.Relation

class FoodWithRecipe(

    @Embedded
    val foodEntity: FoodEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "food_id"
    )
    val foodRecipeEntity: FoodRecipeEntity?

)