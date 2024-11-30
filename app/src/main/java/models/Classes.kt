package models

import android.widget.ImageView

data class Classes(
    val name: String,
    val description: String,
    val image: String,
    val hp: Int,
    val fp: Int,
    val strength: Int,
    val dexterity: Int,
    val intelligence: Int,
    val faith: Int,
    val endurance: Int,
)
