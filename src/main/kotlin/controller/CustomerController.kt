package org.example.controller

import jakarta.validation.Valid
import org.example.dto.CustomerPatch
import org.example.dto.CustomerRequest
import org.example.dto.CustomerResponse
import org.example.service.CustomerService
import org.springframework.data.domain.Page
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/customers")
class CustomerController(private val customerService: CustomerService) {

    @PostMapping
    fun createCustomer(
        @Valid @RequestBody request: CustomerRequest
    ): ResponseEntity<CustomerResponse> {
        val response =
            customerService.createCustomer(request)
        return ResponseEntity.status(HttpStatus.CREATED)
            .body(response)
    }

    @GetMapping("/{id}")
    fun getCustomer(
        @PathVariable id: Long
    ): CustomerResponse {
        return customerService.getCustomerById(id)
    }

    @GetMapping
    fun getAllCustomer(): List<CustomerResponse>{
        return customerService.allCustomers
    }


    @PutMapping("/{id}")
    fun updateCustomer(@PathVariable id: Long, @Valid @RequestBody request: CustomerRequest
    ):CustomerResponse {
        return customerService.updateCustomer(id, request)
    }

    @PatchMapping("/{id}")
    fun patchCustomer(@PathVariable id: Long,
                      @Valid @RequestBody request: CustomerPatch
    ): ResponseEntity<CustomerResponse>{

        return ResponseEntity.status(HttpStatus.OK)
            .body(customerService.patchCustomer(id, request) )
    }

    @DeleteMapping("/{id}")
    fun deleteCustomer(@PathVariable id: Long): ResponseEntity<Void> {
        println("Deleted from controller")
        customerService.deleteCustomerByID(id)
        return ResponseEntity.noContent().build()
    }

    @GetMapping
    fun getCustomersPage(
        @RequestParam(defaultValue = "0") page: Int,
        @RequestParam(defaultValue = "10") size: Int
    ): Page<CustomerResponse> {
        return customerService.getCustomersPage(page, size)
    }
}