package com.example.projekt_zespolowy.ui.theme

import androidx.lifecycle.ViewModel
import com.example.projekt_zespolowy.backend.dataclasses.Courses
import com.example.projekt_zespolowy.backend.dataclasses.UserCourse
import com.example.projekt_zespolowy.backend.dataclasses.Users

class dataViewModel : ViewModel() {

        var specyfic_course : String = ""

        fun updatePerson(name: String){
            specyfic_course = name
        }

    var specyfic_user_id : Int = 1

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

    var actural_course : Int = 1

    fun updateCourse(Id : Int){
        actural_course = Id
    }
    fun clearData(){
        specyfic_profil=""
        specyfic_course=""

    }


    fun GetCouses(List : ArrayList<Courses>, UserID: Int): MutableList<Courses>{
        var MutableList = mutableListOf<Courses>()
        for( item in List)
        {
            if(item.ID == UserID)
                MutableList.add(item)
        }

        return MutableList
    }


    fun GetUsersForCourse(List: List<UserCourse>, CourseID : Int) : MutableList<Int>
    {
        var howMany : Int = 0
        var MutableList = mutableListOf<Int>()
        for(item in List)
        {
            if(item.Course_ID == CourseID){
                MutableList.add(item.User_ID)
                howMany = howMany + 1
            }
        }
        return MutableList
    }

    fun GetUsersById(List : List<Users>, List2 : List<Int>) : MutableList<Users>
    {
        var MutableList = mutableListOf<Users>()
        for(user in List){
            for(index in List2){
                if(user.ID == index)
                {
                    MutableList.add(user)
                }
            }

        }
        return MutableList
    }


}

