package EldenRingApi

import models.BossesResponse
import models.Classes
import models.ClassesResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface EldenRingApiService {
    @GET("classes")
    fun getClasses(@Query("limit") limit: Int = 20): Call<ClassesResponse>

    @GET("bosses")
    fun getBossesByName(@Query("name") name: String): Call<BossesResponse>
}