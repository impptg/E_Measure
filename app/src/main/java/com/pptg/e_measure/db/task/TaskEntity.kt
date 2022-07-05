package com.pptg.e_measure.db.task

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "Task")
class TaskEntity {
    @PrimaryKey(autoGenerate = false)
    @SerializedName("id")
    var taskID:Int = 0

    @ColumnInfo(name = "name")
    var name:String = "name"

    @ColumnInfo(name = "disp")
    var disp:String = "disp"

    @ColumnInfo(name = "attr")
    var attr:String = "attr"

    @ColumnInfo(name = "lim_l")
    var lim_l:String = "lim_l"

    @ColumnInfo(name = "lim_r")
    var lim_r:String = "lim_r"
}