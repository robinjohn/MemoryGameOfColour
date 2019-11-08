package com.example.android.colourmemory.ui.main

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = arrayOf(Score::class), version = 1)
public abstract class ScoreDatabase: RoomDatabase() {
    abstract fun scoreDao(): ScoreDao

    companion object {
        @Volatile
        private var dbInstance: ScoreDatabase? = null

        fun getDatabase(context: Context): ScoreDatabase {
            val tempInstance = dbInstance
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ScoreDatabase::class.java,
                    "score_database"
                ).build()
                dbInstance = instance
                return instance
            }
        }
    }
}