package com.example.newzapp.ui.theme

import android.content.Context
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.workDataOf
import com.example.newzapp.DataBase.DDataBase


class Repositry(context: Context) {


    val databaseee = DDataBase.getDatabase(context = context)
    val workmanager = WorkManager.getInstance(context)

    fun insertentity(title: String, Description: String, httpurl: String) {

        val workRequest = OneTimeWorkRequestBuilder<workerr>()
            .setInputData(workDataOf("URL" to httpurl,
            "title" to title,
            "description" to Description))
            .build()
        workmanager.enqueue(workRequest)


    }

    suspend fun deleteentity(id: Int) {
        databaseee.user_content().deletedetail(id)
    }


}