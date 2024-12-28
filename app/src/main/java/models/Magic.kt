package models

data class Magic(
    val name: Int,
    val description: Int,
    val type: Int,
    val attribute: Attributes,
    val image: Int
)

data class Attributes(
    val int: Int,
    val faith: Int,
    val arcane: Int
)