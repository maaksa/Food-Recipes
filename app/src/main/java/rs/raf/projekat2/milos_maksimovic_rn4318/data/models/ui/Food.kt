package rs.raf.projekat2.milos_maksimovic_rn4318.data.models.ui

import java.util.*

data class Food(
    var id: String,
    val imageURL: String,
    var date: String,
    var categoryName: String,
    var foodName: String,
    var publisher: String,
    var score: Double
)