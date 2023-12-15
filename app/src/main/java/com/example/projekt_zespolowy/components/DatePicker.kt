package com.example.projekt_zespolowy.components

import android.widget.CalendarView
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import java.util.Calendar

@Composable
fun ShowDatePicker(onDateSelected: (Int, Int, Int) -> Unit) {
    var selectedDate by remember { mutableStateOf(Calendar.getInstance()) }

    AndroidView(
        { context ->
            CalendarView(context).apply {
                setOnDateChangeListener { _, year, month, dayOfMonth ->
                    selectedDate = Calendar.getInstance().apply {
                        set(Calendar.YEAR, year)
                        set(Calendar.MONTH, month)
                        set(Calendar.DAY_OF_MONTH, dayOfMonth)
                    }
                    onDateSelected(year, month, dayOfMonth)
                }
            }
        },
        modifier = Modifier.wrapContentWidth()
    )
}