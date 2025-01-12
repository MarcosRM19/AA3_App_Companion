package models

data class Classes(
    val name: String,
    val description: String,
    val image: String,
    val stats: Stats
)

data class Stats(
    val vigor: Int,
    val mind: Int,
    val endurance: Int,
    val strength: Int,
    val dexterity: Int,
    val intelligence: Int,
    val faith: Int,
    val arcane: Int
)
