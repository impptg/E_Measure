package com.pptg.e_measure.db.task

import androidx.room.*
import com.pptg.e_measure.db.base.BaseDao
import com.pptg.e_measure.bean.TaskBean

@Dao
interface TaskDao:BaseDao<TaskBean> {
    @Query("select * from Task")
    fun queryTask(): List<TaskBean>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTask(element: TaskBean)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTask(mList: List<TaskBean>)

    @Delete
    fun deleteTask(element: TaskBean)

    @Delete
    fun deleteTask(mList: List<TaskBean>)
}