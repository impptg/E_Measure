package com.pptg.e_measure.db.history

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "history")
class HistoryEntity {
    @PrimaryKey(autoGenerate = false)
    var HistoryID:Int = 0

    @ColumnInfo(name = "s")
    var ss:Int = 1
}