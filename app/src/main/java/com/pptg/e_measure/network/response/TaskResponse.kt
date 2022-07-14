package com.pptg.e_measure.network.response

import com.pptg.e_measure.bean.TaskBean

class TaskResponse(msg: String, code: String, data: List<TaskBean>):
    BaseResponse<List<TaskBean>>(msg, code, data)