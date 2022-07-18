package com.pptg.e_measure.repository.db.task

import androidx.room.*
import com.pptg.e_measure.repository.db.base.BaseDao
import com.pptg.e_measure.bean.TaskBean

@Dao
interface TaskDao: BaseDao<TaskBean> {
    @Query("select * from Task")
    fun queryTask(): List<TaskBean>

    @Query("select * from Task WHERE TaskID =:ID")
    fun queryTask(ID:String): TaskBean

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTask(element: TaskBean)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTask(mList: List<TaskBean>)

    @Delete
    fun deleteTask(element: TaskBean)

    @Delete
    fun deleteTask(mList: List<TaskBean>)

    @Query("delete from Task")
    fun deleteAllTask()
}