package org.example.dto

import org.example.entity.Address
import org.example.entity.CustomerStatus


class CustomerResponse (

    var firstName: String? = null,
    var lastName: String? = null,
    var email: String? = null,
    var phoneNumber: String? = null,
    var age: Int? = null,
    var status: CustomerStatus? = null,
    var address: AddressRequest? = null
)