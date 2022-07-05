package com.pptg.e_measure.db.history

import androidx.room.*

@Dao
interface HistoryDao {
    @Query("select * from History where historyID = :historyID")
    fun queryHistory(historyID:Int):HistoryEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertHistory(element: HistoryEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertHistory(mList: List<HistoryEntity>)

    @Delete
    fun deleteHistory(element: HistoryEntity)

    @Delete
    fun deleteHistory(mList: List<HistoryEntity>)
}