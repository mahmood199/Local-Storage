package com.example.localstorage.data.dao

import androidx.room.*
import com.example.localstorage.data.model.Employee

@Dao
interface EmployeeDao {

    @Insert
    suspend fun addEmployee(employee: Employee)

    @Update
    suspend fun updateEmployee(employee: Employee)

    @Delete
    suspend fun deleteEmployee(employee: Employee)

    @Query("Select * from employee")
    suspend fun getAllEmployees(): List<Employee>

}