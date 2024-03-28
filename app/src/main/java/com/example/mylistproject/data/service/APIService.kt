package com.example.mylistproject.data.service

import com.example.mylistproject.domain.model.Item
import retrofit2.Response
import retrofit2.http.GET

/**
 *  We create one interface class which will be used for declaration of all API calling functions.
 */
interface APIService {
    @GET("https://fetch-hiring.s3.amazonaws.com/hiring.json")
    suspend fun getItems(
    ): Response<ArrayList<Item>>
}