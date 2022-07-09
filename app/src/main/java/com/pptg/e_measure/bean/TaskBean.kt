package com.pptg.e_measure.bean

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

/**
 * 任务报表
 * 1. 数据库存储
 * 2. 网络解析
 */

@Entity(tableName = "Task")
class TaskBean {
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

//{
//    "msg":"success",
//    "code":0,
//    "data":[
//    {
//        "id":1,
//        "name":"CZ2220",
//        "disp":"复式交分道岔关键数据测量分析表",
//        "attr":"21-22(翼轨咽喉口）1,21-22(翼轨咽喉口）2,21-22(翼轨咽喉口）3,钝角顶点到外基本...",
//        "lim_l":"388,388,388,319",
//        "lim_r":"408,408,408,339"
//    },
//    {
//        "id":2,
//        "name":"CZ2221",
//        "disp":"复式交分道岔关键数据测量分析表2",
//        "attr":"21-22(翼轨咽喉口）1,21-22(翼轨咽喉口）2,21-22(翼轨咽喉口）3",
//        "lim_l":"388,388,388",
//        "lim_r":"408,408,408"
//    }
//    ]
//}