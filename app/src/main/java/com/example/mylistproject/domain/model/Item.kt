package com.example.mylistproject.domain.model

/**
 * Represents an item in a list.
 * @property id The unique identifier of the item.
 * @property listId The identifier of the list to which the item belongs.
 * @property name The name of the item (nullable).
 */
data class Item(
    val id: Int,
    val listId: Int,
    val name: String?
)
