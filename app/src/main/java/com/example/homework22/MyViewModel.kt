package com.example.homework22

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MyViewModel  @Inject constructor(var repo:Repository) : ViewModel() {
    private val _uiState = MutableLiveData<UIState>(UIState.Empty)
    val uiState: LiveData<UIState> = _uiState

    fun getData() {
        _uiState.value = UIState.Processing
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                try {
                    val bitcoin = repo.getMeteo2()
                    if (bitcoin.isSuccessful) {
                        val data = bitcoin.body()
                        val text = "timezone: ${data?.timezone} \n" +
                                "elevation: ${data?.elevation} \n" +
                                "latitude: ${data?.latitude} \n" +
                                "longitude: ${data?.longitude}"
                        _uiState.postValue(UIState.Result(text))
                    } else _uiState.postValue(UIState.Error("Error Code ${bitcoin.code()}"))
                } catch (e: Exception) {
                    _uiState.postValue(UIState.Error(e.localizedMessage))
                }
            }
        }
    }
    sealed class UIState {
        object Empty : UIState()
        object Processing : UIState()
        class Result(val title: String) : UIState()
        class Error(val description: String) : UIState()
    }
}