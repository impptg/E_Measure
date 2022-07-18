package com.pptg.e_measure.repository.network.response

import com.pptg.e_measure.bean.ChangePswdBean

class ChangePswdResponse(msg: String, code: String, data: ChangePswdBean):
    BaseResponse<ChangePswdBean>(msg, code, data)