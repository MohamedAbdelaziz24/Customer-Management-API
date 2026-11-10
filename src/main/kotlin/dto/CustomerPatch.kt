package org.example.dto

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.Max
import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Null
import org.example.entity.CustomerStatus

data class CustomerPatch(
    val firstName: String?,
    val lastName: String?,
    @field:Email
    val email: String? ,
    val phoneNumber: String? ,
    @field:Min(18)
    @field:Max  (100)
    val age: Int ?,
    val status: CustomerStatus?,
    val address: AddressRequest? ,
)