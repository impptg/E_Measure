package com.pptg.e_measure.bean

data class DashboardBean(
    val name:String,
    val disp:String,
    val time:String,
    val status:DashboardEnum
)

enum class DashboardEnum{
    TO_DO,TO_SUBMIT,REVIEWING,MODIFY,FINISH
}