package com.example.projekt_zespolowy.backend.DataHelpers

import Connection
import android.os.AsyncTask
import com.example.projekt_zespolowy.backend.dataclasses.Courses
import com.example.projekt_zespolowy.backend.dataclasses.UserCourse

class DataHelperUserCourse {



    private var isConnected = false
    private lateinit var query:String
    private var recordCount:Int=0
    private var functionType:Int = 0
    lateinit var records : ArrayList<UserCourse>
    lateinit var connection: Connection

    inner class SyncData : AsyncTask<String,String,String>(){
        private var message = "No Connection or Windows FireWall"
        override fun onPreExecute() {
            records = ArrayList<UserCourse>()
            records.clear()
            recordCount = 0;

        }

        override fun doInBackground(vararg params: String?): String {
            var myConn = connection?.conn()
            if(myConn == null){
                isConnected = false
            }
            else{
                val statement = myConn!!.createStatement()
                val cursor = statement.executeQuery(query)
                if(cursor != null) {
                    while (cursor!!.next()) {
                        try {
                            records?.add(
                                UserCourse(
                                    cursor!!.getInt("User_ID"),
                                                               )
                            )
                            recordCount++
                        }catch (ex:Exception){
                            ex.printStackTrace()
                        }
                    }
                    message = "Found $recordCount"
                    isConnected = true
                }
                else{
                    message = "There is no records"
                    isConnected = false
                }
            }
            return message
        }
    }


    fun getCourses(){
        query ="SELECT COUNT(User_ID) FROM Course_joining_users WHERE Course_ID = 3"
        SyncData().execute("")
    }


}