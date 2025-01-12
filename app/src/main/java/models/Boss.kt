package models

data class Boss(
    val id: String,
    val name: String,
    val image: String?,
    val region: String?,
    val description: String?,
    val location: String?
)

data class BossesResponse(
    val data: List<Boss>
)
