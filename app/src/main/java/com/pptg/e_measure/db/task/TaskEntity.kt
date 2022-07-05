package com.pptg.e_measure.db.task

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Task")
class TaskEntity {
    @PrimaryKey(autoGenerate = false)
    val taskID:Int = 0

    @ColumnInfo(name = "name")
    val name:String = "name"

    @ColumnInfo(name = "disp")
    val disp:String = "disp"

    @ColumnInfo(name = "attr")
    val attr:String = "attr"

    @ColumnInfo(name = "lim_l")
    val lim_l:String = "lim_l"

    @ColumnInfo(name = "lim_r")
    val lim_r:String = "lim_r"
}