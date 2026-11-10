package org.example.config

import org.example.entity.Address
import org.example.entity.Customer
import org.example.entity.CustomerStatus
import org.example.repository.CustomerRepository
import org.springframework.context.event.ContextRefreshedEvent
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Component

@Component
class DataInitializer(
    private val customerRepository: CustomerRepository
) {

    @EventListener(ContextRefreshedEvent::class)
    fun insertCustomers() {
        if (customerRepository.count() == 0L) {
            val customers = (1..10).map { i ->

                Customer(
                    firstName = "FirstName$i",
                    lastName = "LastName$i",
                    email = "customer$i@gmail.com",
                    phoneNumber = "010000000$i",
                    age = 20 + i,
                    status =  CustomerStatus.ACTIVE,
                    address = Address(
                        street = "Street $i",
                        city = "Cairo",
                        country = "Egypt",
                        postalCode = 11000 + i
                    )
                )
            }

            customerRepository.saveAll(customers)

            println("10 Customers inserted successfully!")
        }
    }
}