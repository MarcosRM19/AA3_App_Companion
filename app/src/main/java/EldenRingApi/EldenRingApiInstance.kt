package EldenRingApi

import EldenRingApi.EldenRingApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object EldenRingApiInstance
{
    private const val BASE_URL = "https://eldenring.fanapis.com/api/"

    val apiService: EldenRingApiService by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        retrofit.create(EldenRingApiService::class.java)
    }
}