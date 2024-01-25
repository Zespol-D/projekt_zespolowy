package com.example.projekt_zespolowy.backend.DataHelpers

import Connection
import android.os.AsyncTask

class DataHelperLogin {

        private var isConnected = false
        private var Id : Int = 0
        private lateinit var query:String

    lateinit var connection: Connection


        inner class SyncData : AsyncTask<String, String, String>(){
            private var message = "No Connection or Windows FireWall"
            override fun onPreExecute() {

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
                                Id = cursor!!.getInt("ID")
                            }catch(ex:Exception){
                                ex.printStackTrace()
                            }
                        }


                        isConnected = true
                    }
                    else{
                        message = "false"
                        isConnected = false
                    }
                }
                return message
            }
        }
        fun getProfil(newQuerry: String){
            query = newQuerry
            SyncData().execute("")
        }


        fun getIDUser() : Int{
            return Id
        }



    }
