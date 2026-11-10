package org.example.dto

import jakarta.validation.constraints.NotBlank

class AddressRequest (
    @field:NotBlank(message = "Street is Required")
     var street: String? = null,
    @field:NotBlank(message = "City is Required")
     var city: String? = null,
    @field:NotBlank(message = "Country is Required")
     var country: String? = null,
    @field:NotBlank
    var postalCode: Int? = null,
)