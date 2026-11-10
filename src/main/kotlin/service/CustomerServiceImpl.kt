
package org.example.service

import org.example.dto.AddressRequest
import org.example.dto.CustomerPatch
import org.example.dto.CustomerRequest
import org.example.dto.CustomerResponse
import org.example.entity.Address
import org.example.entity.Customer
import org.example.exception.CustomerNotFoundException
import org.example.repository.CustomerRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class CustomerServiceImpl(private val customerRepository: CustomerRepository) : CustomerService {

    // 1. Create Customer
    override fun createCustomer(request: CustomerRequest): CustomerResponse {
        val address = request?.address?.let {
            Address(
                street = it.street,
                city = it.city,
                country = it.country,
                postalCode = it.postalCode
            )
        }
        val customer = Customer(
            firstName = request?.firstName.toString(),
            lastName = request?.lastName.toString(),
            email = request?.email.toString(),
            phoneNumber = request?.phoneNumber.toString(),
            age = request?.age!!,
            status = request?.status,
            address = address
        )

        val saved = customerRepository.save(customer)

        return toEntity(saved)
    }

    // 2. Get Customer By ID
    override fun getCustomerById(id: Long): CustomerResponse {
        val customer = customerRepository.findById(id)
            .orElseThrow {
                CustomerNotFoundException()
            }
        return toEntity(customer)
    }

    // 3. Get All Customers
    override val allCustomers: List<CustomerResponse>
        get() = customerRepository.findAll()
            .map { toEntity(it) }

    // 4. Update Customer
    override fun updateCustomer(id: Long, request: CustomerRequest?): CustomerResponse {

        var customer = customerRepository.findById(id)
            .orElseThrow {
                CustomerNotFoundException()
            }

        customer.firstName = request?.firstName.toString()
        customer.lastName = request?.lastName.toString()
        customer.email = request?.email.toString()
        customer.phoneNumber = request?.phoneNumber.toString()
        customer.age = request?.age!!
        customer.status = request?.status

        var cstAddress = Address()

        cstAddress.street = request?.address?.street.toString()
        cstAddress.city = request?.address?.city.toString()
        cstAddress.country = request?.address?.country.toString()
        cstAddress.postalCode = request?.address?.postalCode

        customer.address = cstAddress

        return toEntity(customerRepository.save(customer))
    }

    // 5. Partial Update Customer
    override fun patchCustomer(id: Long, request: CustomerPatch?): CustomerResponse {

        val customer = customerRepository.findById(id)
            .orElseThrow {
                CustomerNotFoundException()
            }

        // Update only provided fields
        request?.firstName?.let { customer.firstName = it }
        request?.lastName?.let { customer.lastName = it }
        request?.email?.let { customer.email = it }
        request?.phoneNumber?.let { customer.phoneNumber = it }
        request?.age?.let { customer.age = it }
        request?.status?.let { customer.status = it }

        // Partial address update
        request?.address?.let { addressRequest ->
            val address =  Address()
            addressRequest.street?.let { address.street = it }
            addressRequest.city?.let { address.city = it }
            addressRequest.country?.let { address.country = it }
            addressRequest.postalCode?.let { address.postalCode=it}

            customer.address = address
        }

        return toEntity(customerRepository.save(customer))
    }

    // 6. Delete Customer
    override fun deleteCustomerByID(id: Long) {
        if (!customerRepository.existsById(id)) {
            throw CustomerNotFoundException()
        }
        customerRepository.deleteById(id)
    }


    // 7. Pagination
    override fun getCustomersPage(page: Int, size: Int): Page<CustomerResponse> {
        require(page >= 0) {
            "Page number cannot be negative"
        }

        require(size in 1..100) {
            "Page size must be between 1 and 100"
        }

        val pageable: Pageable = PageRequest.of(
            page,
            size,
            Sort.by("customerID").ascending()
        )

        return customerRepository.findAll(pageable)
            .map { toEntity(it) }
    }

    // Mapping Entity to DTO
    private fun toEntity(customer: Customer): CustomerResponse {

        val address = customer.address?.let {
            AddressRequest(
                street = it.street,
                city = it.city,
                country = it.country,
                postalCode = it.postalCode

            )
        }

        return CustomerResponse(
            firstName = customer.firstName,
            lastName = customer.lastName,
            email = customer.email,
            phoneNumber = customer.phoneNumber,
            age = customer.age,
            status = customer.status,
            address = address
        )
    }
}
