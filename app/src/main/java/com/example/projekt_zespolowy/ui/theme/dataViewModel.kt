package com.example.projekt_zespolowy.ui.theme

import androidx.lifecycle.ViewModel

class dataViewModel : ViewModel() {

        var specyfic_course : String = ""

        fun updatePerson(name: String){
            specyfic_course = name
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


}
