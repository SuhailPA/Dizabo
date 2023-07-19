package com.example.dizabo.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dizabo.data.getalldata.Data
import com.example.dizabo.repository.DizaboRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val repository: DizaboRepository) : ViewModel() {

    val homeItems = MutableLiveData<List<Data>>()

    init {
        getAllHomeData()
    }

    private fun getAllHomeData() {
        viewModelScope.launch {
            repository.homeGetAllData().collect {
                if (it.isSuccessful) {
                    homeItems.value = it.body()?.data
                }
            }


        }
    }
}