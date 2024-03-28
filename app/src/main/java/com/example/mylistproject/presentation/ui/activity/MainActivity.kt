package com.example.mylistproject.presentation.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mylistproject.data.networkmodule.RetrofitInstanceModule
import com.example.mylistproject.data.repository.ItemRepositoryImpl
import com.example.mylistproject.databinding.ActivityMainBinding
import com.example.mylistproject.domain.model.Item
import com.example.mylistproject.domain.usecase.GetItemsGroupedByListIdUseCaseImpl
import com.example.mylistproject.presentation.ui.adapter.ItemRecyclerAdapter
import com.example.mylistproject.presentation.ui.factories.ItemViewModelFactory
import com.example.mylistproject.presentation.ui.viewmodels.MyListViewModel

/**
 * Main activity responsible for displaying a list of items grouped by list ID.
 */
class MainActivity : AppCompatActivity() {
    private lateinit var activityMainBinding: ActivityMainBinding
    private lateinit var itemAdapter: ItemRecyclerAdapter
    private lateinit var myListViewModel: MyListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        setUpRecyclerView()
        setUpViewModel()
        observeViewModel()
    }

    /**
     * Sets up the RecyclerView to display the list of items.
     */
    private fun setUpRecyclerView() {
        itemAdapter = ItemRecyclerAdapter()
        activityMainBinding.itemRecyclerView.apply {
            adapter = itemAdapter
            layoutManager = LinearLayoutManager(this@MainActivity)
        }
    }

    /**
     * Sets up the ViewModel to interact with the data layer and observe data changes.
     */
    private fun setUpViewModel() {
        val apiService = RetrofitInstanceModule().theRetrofitInstance()
        val repository = ItemRepositoryImpl(apiService)
        val getItemsGroupedByListIdUseCase = GetItemsGroupedByListIdUseCaseImpl(repository)
        val viewModelFactory = ItemViewModelFactory(repository, getItemsGroupedByListIdUseCase)
        myListViewModel = ViewModelProvider(this, viewModelFactory).get(MyListViewModel::class.java)
        myListViewModel.getGroupedItems()
    }

    /**
     * Observes changes in ViewModel data and updates the UI accordingly.
     */
    private fun observeViewModel() {
        myListViewModel.groupedItems.observe(this, Observer { groupedItems ->
            groupedItems?.let {
                val flattenedList = mutableListOf<Item>()
                it.values.forEach { list ->
                    flattenedList.addAll(list)
                }
                itemAdapter.setData(flattenedList)
            } ?: run {
                Toast.makeText(this, "No items found", Toast.LENGTH_SHORT).show()
            }
        })

        myListViewModel.isShowProgress.observe(this, Observer { showProgress ->
            activityMainBinding.mainProgressBar.visibility = if (showProgress) View.VISIBLE else View.GONE
        })

        myListViewModel.errorMessage.observe(this, Observer { errorMessage ->
            Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show()
        })
    }
}
