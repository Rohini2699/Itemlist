package com.example.mylistproject.presentation.ui.factories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mylistproject.presentation.ui.viewmodels.MyListViewModel
import com.example.mylistproject.domain.usecase.GetItemsGroupedByListIdUseCase
import com.example.sampleproject.domain.network.repository.ItemRepository

/**
 * Factory class responsible for creating instances of MyListViewModel.
 * @param itemRepository The repository for fetching item data.
 * @param getItemsGroupedByListIdUseCase The use case for grouping items by listId.
 */
class ItemViewModelFactory(
    private val itemRepository: ItemRepository,
    private val getItemsGroupedByListIdUseCase: GetItemsGroupedByListIdUseCase
) : ViewModelProvider.Factory {

    /**
     * Creates an instance of the specified ViewModel class.
     * @param modelClass The class of the ViewModel to create.
     * @return An instance of the ViewModel.
     * @throws IllegalArgumentException if the ViewModel class is unknown.
     */
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MyListViewModel::class.java)) {
            return MyListViewModel(itemRepository, getItemsGroupedByListIdUseCase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
