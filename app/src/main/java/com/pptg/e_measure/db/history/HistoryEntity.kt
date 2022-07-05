package com.pptg.e_measure.db.history

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "History")
class HistoryEntity {
    @PrimaryKey(autoGenerate = false)
    var historyID:Int = 0

    @ColumnInfo(name = "name")
    var name:String = "name"

    @ColumnInfo(name = "disp")
    val disp:String = "disp"

    @ColumnInfo(name = "value")
    val value:String = "value"

    @ColumnInfo(name = "attr")
    val attr:String = "attr"

    @ColumnInfo(name = "lim_l")
    val lim_l:String = "lim_l"

    @ColumnInfo(name = "lim_r")
    val lim_r:String = "lim_r"

    @ColumnInfo(name = "createTime")
    val createTime:String = "createTime"

    @ColumnInfo(name = "taskID")
    val taskID:String = "taskID"
}