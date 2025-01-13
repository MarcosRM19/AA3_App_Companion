package CelesteApi

import models.CelesteResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface CelesteApiService {
    @GET("games?key=42e163dc3362478595856a4bb01cdfa9&page=2&search=celeste")
    fun getApi(
        @Query("apiKey") apiKey: String,
        @Query("limit") limit: Int = 20
    ): Call<CelesteResponse>
}