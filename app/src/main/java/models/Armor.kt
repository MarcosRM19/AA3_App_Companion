package models

data class Armor(
    val name: Int,
    val description: Int,
    val DamageNegation: DamageNegation,
    val image: Int
)

data class DamageNegation(
    val Standard: Double,
    val Strike: Double,
    val Slash: Double,
    val Pierce: Double,
    val Magic: Double,
    val Fire: Double,
    val Lightning: Double,
    val Holy: Double,
)

