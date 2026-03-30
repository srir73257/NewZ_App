package com.example.newzapp.ui.theme

import android.content.Context

import androidx.work.Worker
import androidx.work.WorkerParameters
import com.example.newzapp.DataBase.DDataBase
import com.example.newzapp.DataBase.Entity
import okhttp3.OkHttpClient
import okhttp3.Request

import java.io.File

class workerr(
    context: Context,
    params: WorkerParameters
) : Worker(context, params) {

    override fun doWork(): Result {

        val imageurl = inputData.getString("URL")?:" "

        val title =
            inputData.getString("title")
                ?: "No title"

        val description =
            inputData.getString("description")
                ?: "No description"

        val database =
            DDataBase.getDatabase(applicationContext)

        var imagePath = "NOTHING"

        try {

            if (!imageurl.isNullOrBlank()) {

                val response =
                    OkHttpClient()
                        .newCall(
                            Request.Builder()
                                .url(imageurl)
                                .build()
                        )
                        .execute()

                val bytes =
                    response.body?.bytes()

                if (bytes != null) {

                    val folder =
                        applicationContext.filesDir

                    val file =
                        File(
                            folder,
                            "img_${System.currentTimeMillis()}.jpg"
                        )

                    file.writeBytes(bytes)

                    imagePath = file.absolutePath
                }
            }

        } catch (e: Exception) {


            e.printStackTrace()
        }

        val entity = Entity(
            image_URL = imagePath,
            title = title,
            description = description
        )

        database
            .user_content()
            .insertdetail(entity)

        return Result.success()
    }
}