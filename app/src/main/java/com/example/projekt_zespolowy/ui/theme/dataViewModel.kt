package com.example.projekt_zespolowy.ui.theme

import androidx.lifecycle.ViewModel
import com.example.projekt_zespolowy.backend.dataclasses.Courses

class dataViewModel : ViewModel() {

        var specyfic_course : String = ""

        fun updatePerson(name: String){
            specyfic_course = name
        }

    var specyfic_user_id : Int = 0

    fun UpdateSpecyficUserId(id : Int){
        specyfic_user_id = id
    }

    var specyfic_profil : String =""
    fun update_profil(name: String){
        specyfic_profil = name
    }

    fun getPrifile() :String{
        return specyfic_profil
    }

    fun clearData(){
        specyfic_profil=""
        specyfic_course=""

    }


    fun GetCousesForUser(List : List<Courses>, UserID: Int): MutableList<Courses>{
        var MutableList = mutableListOf<Courses>()
        for( item in List)
        {
            if(item.ID == UserID)
                MutableList.add(item)
        }

        return MutableList


    }

}
