package rs.raf.projekat2.milos_maksimovic_rn4318.data.models.ui

import java.io.Serializable

data class FoodRecipe(
    val id: Int,
    val categoryName: String,
    val categoryImgUrl: String,
    val score: Double,
    val ingredients: String
) : Serializable