package CelesteApi

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object CelesteApiInstance {
    private const val BASE_URL = "https://api.rawg.io/api/"

    val apiService: CelesteApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CelesteApiService::class.java)
    }
}