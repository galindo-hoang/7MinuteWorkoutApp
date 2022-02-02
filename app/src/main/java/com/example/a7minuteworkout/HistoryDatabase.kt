package com.example.a7minuteworkout

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database (entities = [HistoryEntity::class], version = 1)
abstract class HistoryDatabase:RoomDatabase() {
    abstract fun historyDao():HistoryDao

    companion object {
        private var Instance: HistoryDatabase? = null

        fun getInstance(context: Context):HistoryDatabase{
            var instance = Instance
            if(instance == null){
                instance = Room.databaseBuilder(
                    context.applicationContext,
                    HistoryDatabase::class.java,
                    "History").fallbackToDestructiveMigration().build()
                this.Instance = instance
            }
            return instance
        }
    }
}