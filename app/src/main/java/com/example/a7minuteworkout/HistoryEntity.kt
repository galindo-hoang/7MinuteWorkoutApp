package com.example.a7minuteworkout

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity (tableName = "History")
data class HistoryEntity(
    @PrimaryKey (autoGenerate = true)
    val Id:Int = 0,
    val Date:String
)