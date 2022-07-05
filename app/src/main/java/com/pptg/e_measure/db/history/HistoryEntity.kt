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
    var disp:String = "disp"

    @ColumnInfo(name = "varue")
    var varue:String = "varue"

    @ColumnInfo(name = "attr")
    var attr:String = "attr"

    @ColumnInfo(name = "lim_l")
    var lim_l:String = "lim_l"

    @ColumnInfo(name = "lim_r")
    var lim_r:String = "lim_r"

    @ColumnInfo(name = "createTime")
    var createTime:String = "createTime"

    @ColumnInfo(name = "taskID")
    var taskID:String = "taskID"
}