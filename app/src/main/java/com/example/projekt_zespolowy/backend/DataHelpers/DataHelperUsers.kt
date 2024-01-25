package com.example.projekt_zespolowy.backend.DataHelpers
import Connection
import android.os.AsyncTask
import com.example.projekt_zespolowy.backend.dataclasses.Users

class DataHelperUsers {

    private var isConnected = false
    private lateinit var query:String
    private var recordCount:Int=0
    private var functionType:Int = 0
    lateinit var records : ArrayList<Users>
    lateinit var connection: Connection

    inner class SyncData : AsyncTask<String,String,String>(){
        private var message = "No Connection or Windows FireWall"
        override fun onPreExecute() {
            records = ArrayList<Users>()
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
                                Users(

                                    cursor!!.getInt("ID"),
                                    cursor!!.getString("FirstName"),
                                    cursor!!.getString("LastName")

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
    fun getUsers(){
        query = "SELECT ID, FirstName, LastName FROM dbo.Users"
        SyncData().execute("")
    }



}