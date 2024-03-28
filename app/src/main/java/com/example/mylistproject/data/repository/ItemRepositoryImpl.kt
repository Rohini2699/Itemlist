package com.example.mylistproject.data.repository

import com.example.mylistproject.data.service.APIService
import com.example.mylistproject.domain.model.Item
import com.example.sampleproject.domain.network.repository.ItemRepository
import retrofit2.HttpException
import java.io.IOException
import com.example.mylistproject.data.utils.Result

class ItemRepositoryImpl(private val apiService: APIService) : ItemRepository {

    /**
     * Fetches the list of items from the API.
     * @return Result object containing either the list of items on success or an error message on failure.
     */
    override suspend fun getItems(): Result<List<Item>> {
        return try {
            val response = apiService.getItems()
            if (response.isSuccessful) {
                Result.Success(response.body() ?: emptyList())
            } else {
                Result.Error("HTTP Error: ${response.code()}")
            }
        } catch (e: IOException) {
            Result.Error("Network Error: ${e.message}")
        } catch (e: HttpException) {
            Result.Error("HTTP Error: ${e.message}")
        } catch (e: Exception) {
            Result.Error("Error: ${e.message}")
        }
    }
}