package models

data class CelesteResponse(
    val count: Int,
    val results: List<CelesteGame>
)

data class CelesteGame(
    val id: Int,
    val name: String,
    val released: String?,
    val background_image: String?,
    val rating: Double,
    val ratings_count: Int,
    val platforms: List<PlatformWrapper>?
)

data class PlatformWrapper(
    val platform: Platform
)

data class Platform(
    val id: Int,
    val name: String,
    val slug: String
)