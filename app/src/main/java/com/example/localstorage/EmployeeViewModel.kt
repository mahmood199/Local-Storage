package com.example.localstorage

import android.app.Application
import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.example.localstorage.data.DBAccessPoint
import com.example.localstorage.data.Employee
import com.example.localstorage.util.FailureStatus
import com.example.localstorage.util.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class EmployeeViewModel() : ViewModel() {


    fun addEmployee(context: Context,employeeName: String, employeeNumber: String) {
        viewModelScope.launch {
            DBAccessPoint.getDB(context.applicationContext).employeeDao()
                .addEmployee(
                    Employee(
                        employeeName = employeeName,
                        employeeNumber = employeeNumber
                    )
                )

        }
    }

    fun getALLEmployees(application: Application) = liveData(Dispatchers.IO) {
        emit(Resource.Loading)
        try {
            emit(Resource.Success(DBAccessPoint.getDB(application).employeeDao().getAllEmployees()))
        } catch (exception: Exception) {
            emit(Resource.Failure(FailureStatus.API_FAIL, message = exception.message ?: "Error!"))
        }
    }


}