package com.example.android.colourmemory.ui.main

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query

@Dao
interface ScoreDao {
    @Query("SELECT * from score_table ORDER BY score ASC")
    fun getRankedScoreList(): LiveData<List<Score>>
}