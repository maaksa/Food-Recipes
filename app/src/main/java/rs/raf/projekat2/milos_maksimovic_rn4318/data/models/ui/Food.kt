package rs.raf.projekat2.milos_maksimovic_rn4318.data.models.ui

import java.util.*

class Food(
    var id: Int,
    val imageURL: String,
    var date: Date = Date(),
    var categoryName: String,
    var foodName: String,
    var publisher: String,
    var score: Double
)