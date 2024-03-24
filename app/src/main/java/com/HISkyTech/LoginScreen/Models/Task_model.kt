package com.HISkyTech.LoginScreen.Models

import com.google.firebase.Timestamp
import com.google.gson.Gson

data class Task_model(

    var title:String="",
    var description:String="",
    var task_date:String="",
    var Catagory:String="",
    var priority:String="",
    var task_id:String="",
    var quantity:String="",
    var amount:String="",
    var userId:String="",
    val createdAt: Timestamp = Timestamp.now()
){
    override fun toString(): String {
        val gson = Gson()
        return gson.toJson(this)
    }

    companion object {
        fun fromString(modelFA: String): Task_model? {
            val gson = Gson()
            return try {
                gson.fromJson(modelFA, Task_model::class.java)
            } catch (e: Exception) {
                null
            }
        }
    }
}
