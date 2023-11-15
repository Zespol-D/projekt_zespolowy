package com.example.projekt_zespolowy.backend

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.sql.Connection
import java.sql.DriverManager

object DatabaseRepository {
    private suspend fun getConnection(): Connection = withContext(Dispatchers.IO) {
        val url = "jdbc:sqlserver://DESKTOP435345:1433;databaseName=NurturGuide;integratedSecurity=true;"
        return@withContext DriverManager.getConnection(url)
    }

    suspend fun getCourses(): List<CourseData> {
        val courses = mutableListOf<CourseData>()

        try {
            val connection = getConnection()
            val statement = connection.createStatement()
            val resultSet = statement.executeQuery("SELECT * FROM Courses")

            while (resultSet.next()) {
                val courseId = resultSet.getInt("ID")
                val courseName = resultSet.getString("Course_name")
                val organizerId = resultSet.getInt("Course_organizer_id")
                val startDate = resultSet.getDate("Start_date")
                val endDate = resultSet.getDate("End_date")
                val price = resultSet.getDouble("Ticket_price")

                val course = CourseData(courseId, courseName, organizerId, startDate, endDate, price)
                courses.add(course)

                // Dodaj logi do śledzenia danych
                println("Pobrano kurs: $course")
            }

            resultSet.close()
            statement.close()
            connection.close()

        } catch (e: Exception) {
            e.printStackTrace()
        }

        return courses
    }
}

