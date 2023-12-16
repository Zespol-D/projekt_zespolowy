package com.example.projekt_zespolowy.backend.dataclasses



//TO będzie DataClass dla głownej strony MainScreen1.kt
data class Courses(
    val Course_name: String,
    val Organizer_name: String,
    val Organizer_LastName: String,
    val Start_date: String,
    val End_date: String,
    val Ticket_price: Float
)
