package com.pptg.e_measure.repository

import com.pptg.e_measure.repository.db.DBManager
import com.pptg.e_measure.repository.network.Api.ApiNet
import com.pptg.e_measure.repository.network.ServiceCreator

object EMRepository {
    private const val TAG = "EMRepository"
    // 创建service
    private val service = ServiceCreator.create<ApiNet>(ApiNet::class.java)

    // 封装Api
    // 用户登陆
    suspend fun postLogin(user_id: String,user_pswd: String) =
        service.Login(user_id,user_pswd)
    // 修改密码
    suspend fun postChangePswd(user_id: String,old_pswd: String,new_pswd: String) =
        service.ChangePswd(user_id, old_pswd, new_pswd)
    // 获取数据报表
    suspend fun getTask() = service.Task()

}