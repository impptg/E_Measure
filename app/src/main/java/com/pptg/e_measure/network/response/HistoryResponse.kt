package com.pptg.e_measure.network.response

import com.pptg.e_measure.bean.HistoryBean

class HistoryResponse(msg: String, code: String, data: HistoryBean):
    BaseResponse<HistoryBean>(msg, code, data) {
}