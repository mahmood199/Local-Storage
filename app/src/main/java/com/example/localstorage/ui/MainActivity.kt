package com.example.localstorage.ui

import android.os.Bundle
import android.text.TextUtils
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.localstorage.data.model.Employee
import com.example.localstorage.util.Resource
import com.example.localstorage.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: EmployeeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this)[EmployeeViewModel::class.java]
        getAllEmployeesAndShow()

        binding.btnAddEmployee.setOnClickListener {
            if (TextUtils.isEmpty(binding.tvName.text).not() &&
                TextUtils.isEmpty(binding.tvNumber.text).not()
            ) {
                viewModel.addEmployee(
                    binding.tvName.text.toString(),
                    binding.tvNumber.text.toString())
                getAllEmployeesAndShow()
            }
        }
    }

    private fun getAllEmployeesAndShow() {
        viewModel.getAllEmployees().observe(this) {
            when (it) {
                is Resource.Default -> {
                    binding.cpiLoading.visibility = View.GONE
                }
                is Resource.Failure -> {
                    binding.cpiLoading.visibility = View.GONE
                }
                is Resource.Loading -> {
                    binding.cpiLoading.visibility = View.VISIBLE
                }
                is Resource.Success -> {
                    setText(it.value)
                }
            }
        }
    }

    private fun setText(value: List<Employee>) {
        var data = ""
        value.forEach {
            data += "${it.id}\n" + it.employeeName + "\n" + it.employeeNumber + "\n\n\n"
        }
        binding.tvEmployees.text = data
    }
}