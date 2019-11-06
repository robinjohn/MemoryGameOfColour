package com.example.android.colourmemory.ui.main

interface MainContract {

    interface MainView {
        fun initializeGame()
        fun evaluateGame(first: Int, second: Int)
    }
}