package models

data class Magic(
    val name: Int,
    val description: Int,
    val Type: Int,
    val attribute: Attributes,
    val image: Int
)

data class Attributes(
    val int: Int,
    val faith: Int,
    val arcane: Int
)