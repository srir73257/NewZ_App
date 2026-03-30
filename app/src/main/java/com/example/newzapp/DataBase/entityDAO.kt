package com.example.newzapp.DataBase

import androidx.appcompat.widget.DialogTitle
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.internal.OpDescriptor

@Dao
interface Daoooooo {

    @Upsert
    fun insertdetail(item: Entity)

    @Query("SELECT EXISTS (SELECT * FROM content_entity WHERE title=:Ttle AND description =:Description)")
    suspend fun check(Ttle: String,Description : String): Boolean

    @Query("SELECT * from content_entity")
    fun display(): Flow<List<Entity>>

    @Query("DELETE FROM content_entity WHERE id=:ID")
    suspend fun deletedetail(ID: Int)


}