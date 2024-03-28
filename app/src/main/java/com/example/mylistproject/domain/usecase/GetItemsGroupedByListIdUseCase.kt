package com.example.mylistproject.domain.usecase

import com.example.mylistproject.domain.model.Item
import com.example.mylistproject.data.utils.Result

/**
 * Use case interface for getting items grouped by list ID.
 */
interface GetItemsGroupedByListIdUseCase {

    /**
     * Executes the use case to get items grouped by list ID.
     * @return A Result object containing a map where keys are list IDs and values are lists of items.
     */
    suspend fun execute(): Result<Map<Int, List<Item>>>
}
