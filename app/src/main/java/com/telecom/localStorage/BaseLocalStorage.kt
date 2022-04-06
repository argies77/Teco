package com.telecom.localStorage

import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.JsonArray
import com.google.gson.JsonParser
import com.telecom.model.KeyLocalStorage


class BaseLocalStorage(private val sharedPreferences: SharedPreferences) {

   fun <T> storeList(key: KeyLocalStorage, objects: List<T>) {
        if (objects.isEmpty()) {
            sharedPreferences.edit().putString(key.toString(), "").commit()
            return
        }
        val gson = Gson()
        val objectsJson = gson.toJson(objects)
        sharedPreferences.edit().putString(key.toString(), objectsJson).commit()
    }

    fun <T> retrieveList(key: KeyLocalStorage, cls: Class<T>): List<T> {
        val list = ArrayList<T>()
        try {
            val gson = Gson()
            val objectsJson = sharedPreferences.getString(key.toString(), "")
            val arry: JsonArray = JsonParser().parse(objectsJson).getAsJsonArray()
            for (jsonElement in arry) {
                list.add(gson.fromJson(jsonElement, cls))
            }
        } catch (exception: IllegalStateException) {
            exception.printStackTrace();
        }
        return list
    }

    fun <T> store(key: KeyLocalStorage, objects: T) {
        if (objects == null) {
            sharedPreferences.edit().putString(key.toString(), "").commit()
            return
        }
        val gson = Gson()
        val objectsJson = gson.toJson(objects)
        sharedPreferences.edit().putString(key.toString(), objectsJson).commit()
    }

    fun <T> retrieve(key: KeyLocalStorage, cls: Class<T>): T? {
        val gson = Gson()
        val objectsJson = sharedPreferences.getString(key.toString(), "")
        //val turnsType = object : TypeToken<Any>() {}.type
        return try {
            //gson.fromJson(objectsJson, turnsType)
            gson.fromJson(objectsJson,cls)
        } catch (exception: IllegalStateException) {
            return null
        }
    }

}

