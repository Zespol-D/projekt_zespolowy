package com.example.projekt_zespolowy

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class SharedViewModel : ViewModel() {
    val selectedDate: MutableState<Triple<Int, Int, Int>?> = mutableStateOf(null)
    fun resetDatePickerData() {
        selectedDate.value = null
    }

    fun setDatePickerData(year: Int, month: Int, dayOfMonth: Int) {
        selectedDate.value = Triple(year, month, dayOfMonth)
    }
}