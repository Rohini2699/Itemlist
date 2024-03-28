package com.example.mylistproject.data.networkmodule

import com.example.mylistproject.data.service.APIService
import com.example.sampleproject.data.utils.Const.Companion.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * In this theRetrofitInstance() function, we build the retrofit instance for generating API calling.
 * We have to pass the base url of the API.
 */
class RetrofitInstanceModule {
    fun theRetrofitInstance(): APIService {
        val API: APIService by lazy {
            Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(APIService::class.java)
        }
        return API
    }
}