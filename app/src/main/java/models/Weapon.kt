package models

data class Weapon(
    val name: Int,
    val description: Int,
    val attackpower: AttackPower,
    val image: Int
)

data class AttackPower(
    val physical: Int,
    val magic: Int,
    val fire: Int,
    val lightning: Int,
    val holy: Int,
    val critical: Int,
)