package com.example.dizabo.data.getalldata

data class Data(
    val createdAt: String,
    val departmentDetails: DepartmentDetails,
    val fkDepartmentId: String,
    val imgLink: String,
    val intMinAmount: Int,
    val intProfitPercentage: Int,
    val intTimeFrameInMonth: Int,
    val pkInvestmentPlanId: String,
    val strCurrency: String,
    val strDescription: String,
    val strTitle: String,
    val updatedAt: String
)