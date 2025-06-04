package com.faris0048.asessment3.ui.screen

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.faris0048.asessment3.model.BangunRuang
import com.faris0048.asessment3.network.ApiStatus
import com.faris0048.asessment3.network.BangunRuangApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    var data = mutableStateOf(emptyList<BangunRuang>())
        private set

    var status = MutableStateFlow(ApiStatus.LOADING)
        private set

    init {
        retriveData()
    }

    fun retriveData() {
        viewModelScope.launch(Dispatchers.IO) {
            status.value = ApiStatus.LOADING
            try {
                data.value = BangunRuangApi.service.getBangunRuang()
                status.value = ApiStatus.SUCCESS
            } catch (e: Exception) {
                Log.d("MainViewModel", "Failure: ${e.message}")
                status.value = ApiStatus.FAILED
            }
        }
    }
}