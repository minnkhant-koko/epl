package com.kwk.epl

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeViewModel: ViewModel() {

    private val _textHolder = MutableLiveData<String>()
    val textHolder: LiveData<String>
        get() = _textHolder

    fun holdInputText(input: String) {
        viewModelScope.launch(Dispatchers.Default) {
            _textHolder.value = input
        }
    }
}