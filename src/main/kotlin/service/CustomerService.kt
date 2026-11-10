package org.example.service

import jakarta.validation.Valid
import org.example.dto.CustomerPatch
import org.example.dto.CustomerRequest
import org.example.dto.CustomerResponse
import org.springframework.data.domain.Page


interface CustomerService {

    fun createCustomer(request: CustomerRequest): CustomerResponse

    fun getCustomerById(id: Long): CustomerResponse

    val allCustomers: List<CustomerResponse>

    fun updateCustomer(id: Long, request: @Valid CustomerRequest?): CustomerResponse

    fun patchCustomer(id: Long, request: @Valid CustomerPatch?): CustomerResponse

    fun deleteCustomerByID(id: Long)

    fun getCustomersPage(page: Int, size: Int): Page<CustomerResponse>

}