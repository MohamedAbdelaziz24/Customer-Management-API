package org.example.dto

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.Max
import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotBlank
import org.example.entity.CustomerStatus


class CustomerRequest(

    @field:NotBlank(message = "First Name Required ")
     val firstName: String,

    @field:NotBlank(message = "Last Name Required ")
     val lastName: String,

    @Email
    @field:NotBlank(message = "Email Required ")
    val email: String ,

    @field:NotBlank(message = "Phone Number is Required")
     val phoneNumber: String ,

    @field:NotBlank(message = "Age is Required")
    @field:Min(18)
    @field:Max  (100)
     val age: Int ,

    @field:NotBlank(message = "Status is Required")
     val status: CustomerStatus ,

    val address: AddressRequest ,

    )


