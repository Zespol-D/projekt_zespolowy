package com.example.projekt_zespolowy.backend.dataclasses



//TO będzie DataClass dla głownej strony MainScreen1.kt
data class Courses(
    val ID: Int,
    val Course_name: String,
    val Course_organizer_id: Int,
    val Start_date: String,
    val End_date: String,
    val Ticket_price: Float
)
