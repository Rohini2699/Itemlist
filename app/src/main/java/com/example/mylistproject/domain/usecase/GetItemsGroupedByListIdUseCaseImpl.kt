package com.example.mylistproject.domain.usecase

import com.example.mylistproject.domain.model.Item
import com.example.mylistproject.data.utils.Result
import com.example.sampleproject.domain.network.repository.ItemRepository

/**
 * Use case implementation responsible for fetching items from the repository and grouping them by listId.
 * @param itemRepository The repository responsible for providing access to item data.
 */
class GetItemsGroupedByListIdUseCaseImpl(private val itemRepository: ItemRepository) :
    GetItemsGroupedByListIdUseCase {

    /**
     * Executes the use case to fetch items from the repository and group them by listId.
     * @return A Result object containing a map where keys are list IDs and values are lists of items.
     */
    override suspend fun execute(): Result<Map<Int, List<Item>>> {
        val result = itemRepository.getItems()
        return if (result is Result.Success) {
            val groupedItems = result.data
                .filter { it.name?.isNotBlank() == true }
                .groupBy { it.listId }
                .toSortedMap()
                .mapValues { (_, items) ->
                    items.sortedWith(
                        compareBy({ it.listId }, { extractNumericValue(it.name ?: "") })
                    )
                } // Sort items by listId and then by name (parsed as integer) within each group
            Result.Success(groupedItems)
        } else {
            result as Result.Error
        }
    }

    /**
     * Extracts the numeric value from a string.
     * @param name The string from which to extract the numeric value.
     * @return The extracted numeric value as an integer.
     */
    private fun extractNumericValue(name: String): Int {
        val numericPart = name.replace(Regex("[^0-9]+"), "")
        return numericPart.toIntOrNull() ?: Int.MAX_VALUE
    }
}
