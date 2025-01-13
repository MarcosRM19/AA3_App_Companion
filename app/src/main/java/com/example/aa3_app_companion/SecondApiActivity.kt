package com.example.aa3_app_companion

import CelesteApi.CelesteApiInstance
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import models.CelesteResponse
import models.ClassesResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SecondApiActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_second_api)

        callApi()
    }

    private fun callApi()
    {
        val apiKey = "42e163dc3362478595856a4bb01cdfa9"

        val call = CelesteApiInstance.apiService.getApi(apiKey)

        call.enqueue(object : Callback<CelesteResponse> {
            override fun onResponse(call: Call<CelesteResponse>, response: Response<CelesteResponse>) {
                if (response.isSuccessful) {
                    val games = response.body()?.results
                    if (games != null) {
                        Log.d("ApiResponse", "Platform received: ${games.size}")
                    }
                    if (games != null) {
                        for (character in games) {
                            Log.d("ApiResponse", "Name: ${character.name}")
                        }
                    }

                } else {
                    Log.d("Api","ApiResponse is not Successful")
                }
            }

            override fun onFailure(call: Call<CelesteResponse>, t: Throwable) {
                Log.d("Api","on Failure")
            }
        })
    }
}