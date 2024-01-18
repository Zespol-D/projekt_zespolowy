package com.example.projekt_zespolowy.backend.DataHelpers

import Connection
import android.os.AsyncTask
import com.example.projekt_zespolowy.backend.dataclasses.Courses

class addCourse {

    private var isConnected = false
    private lateinit var query: String
    private lateinit var query2: String
    private var recordCount: Int = 0
    private var record2Count: Int = 0
    private var functionType: Int = 0
    lateinit var records: ArrayList<Courses>
    lateinit var records2: ArrayList<Courses>
    lateinit var connection: Connection

    inner class SyncData : AsyncTask<String, String, String>() {
        private var message = "No Connection or Windows FireWall"
        override fun onPreExecute() {
            records = ArrayList<Courses>()
            records.clear()
            recordCount = 0;

            //second querry
            records2 = ArrayList<Courses>()
            records2.clear()
            record2Count = 0;
        }

        override fun doInBackground(vararg params: String?): String {
            var myConn = connection?.conn()
            if (myConn == null) {
                isConnected = false
            } else {
                val statement = myConn!!.createStatement()
                val cursor = statement.executeQuery(query)
                if (cursor != null) {
                    while (cursor!!.next()) {
                        try {
                            records?.add(
                                Courses(
                                    cursor!!.getString("Course_name"),
                                    cursor!!.getString("FirstName"),
                                    cursor!!.getString("LastName"),
                                    cursor!!.getString("Start_date"),
                                    cursor!!.getString("End_date"),
                                    cursor!!.getFloat("Ticket_price")
                                )
                            )
                            recordCount++
                        } catch (ex: Exception) {
                            ex.printStackTrace()
                        }
                    }
                    message = "Found $recordCount"
                    isConnected = true
                } else {
                    message = "There is no records"
                    isConnected = false
                }
            }
            return message
        }
    }


    fun getCourses() {
        query =
            "SELECT  [Course_name],[FirstName],[LastName],[Start_date],[End_date],[Ticket_price] FROM [NurturGuide].[dbo].[Od najnowszych]\n"
        SyncData().execute("")
    }
}


