package com.example.projekt_zespolowy

import java.util.Date

data class CourseData(
    val id: Int,
    val courseName: String,
    val courseOrganizerId: Int,
    val startDate: Date,
    val endDate: Date,
    val ticketPrice: Double
)
