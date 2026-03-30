package com.example.newzapp.DataBase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [Entity::class], version = 1, exportSchema = false)
abstract class DDataBase: RoomDatabase() {

    abstract fun user_content(): Daoooooo

    companion object {


        @Volatile
        var INSTANCE: DDataBase? = null

        fun getDatabase(context: Context): DDataBase{
            return INSTANCE?:synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    DDataBase::class.java,
                    "user_db"
                ).build()
                INSTANCE=instance
                instance
            }

        }


    }
}