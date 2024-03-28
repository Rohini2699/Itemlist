package com.example.sampleproject.domain.network.repository

import com.example.mylistproject.domain.model.Item
import com.example.mylistproject.data.utils.Result

/**
 * Repository interface for fetching items list data.
 */
interface ItemRepository {

    /**
     * Fetches the list of items from the API.
     * @return Result object containing either the list of items on success or an error message on failure.
     */
    suspend fun getItems(): Result<List<Item>>
}
