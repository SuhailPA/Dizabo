package com.example.dizabo.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dizabo.data.getalldata.Data
import com.example.dizabo.repository.DizaboRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val repository: DizaboRepository): ViewModel() {

    val homeItems = MutableLiveData<List<Data>>()
    init {
        getAllHomeData()
    }

     fun getAllHomeData()  {
        viewModelScope.launch {
            homeItems.value = repository.homeGetAllData().body()?.data


        }
    }
}