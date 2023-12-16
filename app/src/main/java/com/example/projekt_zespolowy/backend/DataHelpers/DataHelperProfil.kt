package com.example.projekt_zespolowy.backend.DataHelpers


import Connection
import android.os.AsyncTask
import com.example.projekt_zespolowy.backend.dataclasses.Profil


class DatabaseHelperProfil {
    private var isConnected = false
    private lateinit var query:String
    private var recordCount:Int=0
    private var functionType:Int = 0
    lateinit var records : ArrayList<Profil>
    lateinit var connection: Connection

    inner class SyncData : AsyncTask<String,String,String>(){
        private var message = "No Connection or Windows FireWall"
        override fun onPreExecute() {
            records = ArrayList<Profil>()
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
                                Profil(
                                    cursor!!.getString("FirstName"),
                                    cursor!!.getString("LastName"),
                                    cursor!!.getString("Mail"),
                                    cursor!!.getInt("Tel"),
                                    cursor!!.getString("Gender"),
                                    cursor!!.getString("Nationality"),
                                    cursor!!.getInt("Area_code")
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
    fun getProfil(){
        query ="SELECT [FirstName],[LastName],[Mail],[Tel],[Gender],[Nationality],[Area_code] FROM [NurturGuide].[dbo].[Profil]\n"
        SyncData().execute("")
    }
}