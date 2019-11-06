package com.example.android.colourmemory.ui.main

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "score_table")
data class Score(@PrimaryKey @ColumnInfo(name = "name") val name: String, @ColumnInfo(name = "score") val score: Number)
