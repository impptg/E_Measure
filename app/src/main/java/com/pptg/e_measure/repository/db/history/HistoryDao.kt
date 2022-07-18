package com.pptg.e_measure.repository.db.history

import androidx.room.*
import com.pptg.e_measure.bean.HistoryBean

@Dao
interface HistoryDao {
    @Query("select * from History where historyID = :historyID")
    fun queryHistory(historyID:Int): HistoryBean

    @Query("select * from History")
    fun queryHistory(): List<HistoryBean>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertHistory(element: HistoryBean)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertHistory(mList: List<HistoryBean>)

    @Delete
    fun deleteHistory(element: HistoryBean)

    @Delete
    fun deleteHistory(mList: List<HistoryBean>)
}