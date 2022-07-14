package com.pptg.e_measure.network.response

import com.pptg.e_measure.bean.LoginBean

class LoginResponse(msg: String, code: String, data: LoginBean):
    BaseResponse<LoginBean>(msg, code, data)
