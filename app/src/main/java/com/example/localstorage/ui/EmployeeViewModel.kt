package com.example.localstorage.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.example.localstorage.data.db.DBAccessPoint
import com.example.localstorage.data.model.Employee
import com.example.localstorage.util.FailureStatus
import com.example.localstorage.util.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class EmployeeViewModel(application: Application) : AndroidViewModel(application) {


    fun addEmployee(employeeName: String, employeeNumber: String) {
        viewModelScope.launch {
            DBAccessPoint.getDB(getApplication()).employeeDao()
                .addEmployee(
                    Employee(
                        employeeName = employeeName,
                        employeeNumber = employeeNumber
                    )
                )

        }
    }

    fun getAllEmployees() = liveData(Dispatchers.IO) {
        emit(Resource.Loading)
        try {
            emit(Resource.Success(DBAccessPoint.getDB(getApplication()).employeeDao().getAllEmployees()))
        } catch (exception: Exception) {
            emit(Resource.Failure(FailureStatus.API_FAIL, message = exception.message ?: "Error!"))
        }
    }


}