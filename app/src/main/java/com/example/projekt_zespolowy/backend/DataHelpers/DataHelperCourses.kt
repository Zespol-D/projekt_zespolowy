package com.example.projekt_zespolowy.backend.DataHelpers



import Connection
import android.os.AsyncTask
import com.example.projekt_zespolowy.backend.dataclasses.Courses



class DatabaseHelperCourses {
    private var isConnected = false
    private lateinit var query:String
    private var recordCount:Int=0
    private var functionType:Int = 0
    lateinit var records : ArrayList<Courses>
    lateinit var connection: Connection

    inner class SyncData : AsyncTask<String,String,String>(){
        private var message = "No Connection or Windows FireWall"
        override fun onPreExecute() {
            records = ArrayList<Courses>()
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
                val kursyCursor = statement.executeQuery(query)
                if(kursyCursor != null) {
                    while (kursyCursor!!.next()) {
                        try {
                            records?.add(
                                Courses(

                                    kursyCursor!!.getString("Course_name"),
                                    kursyCursor!!.getString("FirstName"),
                                    kursyCursor!!.getString("LastName"),
                                    kursyCursor!!.getString("Start_date"),
                                    kursyCursor!!.getString("End_date"),
                                    kursyCursor!!.getFloat("Ticket_price")
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
    fun getCustomers(){
        query ="SELECT  [Course_name],[FirstName],[LastName],[Start_date],[End_date],[Ticket_price]FROM [NurturGuide].[dbo].[Od najnowszych]\n"
        SyncData().execute("")
    }
}