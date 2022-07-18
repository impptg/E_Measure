package com.pptg.e_measure.repository.db

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.pptg.e_measure.EMApplication
import com.pptg.e_measure.repository.db.history.HistoryDao
import com.pptg.e_measure.bean.HistoryBean
import com.pptg.e_measure.repository.db.task.TaskDao
import com.pptg.e_measure.bean.TaskBean

@Database(entities = [TaskBean::class, HistoryBean::class], version = 1)
abstract class DBManager :RoomDatabase(){
    companion object{
        var single = Single.sin
    }
    abstract fun getTaskDao(): TaskDao
    abstract fun getHistoryDao(): HistoryDao

    private object Single {
        var sin : DBManager = Room.databaseBuilder(
            EMApplication.context,
            DBManager::class.java,
            "User.db"
        )
            .allowMainThreadQueries()
            .build()
    }
}