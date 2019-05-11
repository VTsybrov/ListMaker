package com.cyberoff.listmaker

import android.content.Context
import android.preference.PreferenceManager

class ListDataManager(val context: Context) {

    fun saveList(list: TaskList){
        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context).edit()
        sharedPreferences.putStringSet(list.name, list.tasks.toHashSet())
        sharedPreferences.apply()
    }

    fun readLists(): ArrayList<TaskList>{
        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
        val sharedPreferencesContext = sharedPreferences.all
        val taskLists = ArrayList<TaskList>()
        for (preferenceItem in sharedPreferencesContext){
            val itemHashSet = preferenceItem as HashSet<String>
            val list = TaskList(preferenceItem.key, ArrayList(itemHashSet))
            taskLists.add(list)
        }
        return taskLists
    }
}