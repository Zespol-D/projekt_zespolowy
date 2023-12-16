package com.example.projekt_zespolowy.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

data class ContainerInfo(val text: String) {
    val selectedState = mutableStateOf(false)
}
// zawartość chipsików
val infoContainers = listOf(
    ContainerInfo("#Organizator"),
    ContainerInfo("#Najnowszy"),
    ContainerInfo("#Najstarszy"),
    ContainerInfo("#Najtańszy"),
    ContainerInfo("#Najdroższy")
)

val selectedInfoContainers = mutableStateListOf<ContainerInfo>()

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InfoContainer(info: ContainerInfo) {
    val selectedState = info.selectedState
    // kod kompozycji
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(8.dp)
    ) {
        FilterChip(
            onClick = {
                // Odkliknij zaznaczony chips, jeśli jest zaznaczony
                if (selectedState.value) {
                    selectedState.value = false
                    selectedInfoContainers.remove(info)
                } else {
                    // Zresetuj stan innych elementów
                    infoContainers.forEach { it.selectedState.value = false }
                    selectedState.value = true
                    selectedInfoContainers.clear()
                    selectedInfoContainers.add(info)
                }
            },
            label = {
                Text(info.text)
            },
            selected = selectedState.value,
            leadingIcon = if (selectedState.value) {
                {
                    Icon(
                        imageVector = Icons.Filled.Done,
                        contentDescription = "Done icon",
                        modifier = Modifier
                            .size(FilterChipDefaults.IconSize)
                    )
                }
            } else {
                null
            },
        )
    }
}

@Preview
@Composable
fun InfoContainerListPreview() {
    LazyRow {
        this.items(infoContainers) { infoContainer ->
            InfoContainer(infoContainer)
        }
    }
}
