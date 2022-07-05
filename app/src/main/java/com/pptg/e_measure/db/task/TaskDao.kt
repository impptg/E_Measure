package com.pptg.e_measure.db.task

import androidx.room.*
import com.pptg.e_measure.db.base.BaseDao
import com.pptg.e_measure.db.task.TaskEntity

@Dao
interface TaskDao:BaseDao<TaskEntity> {
    @Query("select * from Task")
    fun queryTask(): List<TaskEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTask(element: TaskEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTask(mList: List<TaskEntity>)

    @Delete
    fun deleteTask(element: TaskEntity)

    @Delete
    fun deleteTask(mList: List<TaskEntity>)
}