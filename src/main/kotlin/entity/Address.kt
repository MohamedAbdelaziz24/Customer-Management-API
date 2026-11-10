package org.example.entity

import com.fasterxml.jackson.annotation.JsonBackReference
import jakarta.persistence.*
import jakarta.validation.constraints.NotBlank

@Entity
@Table(name = "addresses")
class Address (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column("address_id")
    var addressID: Long? = null,

    @NotBlank
    var street: String? = "",

    @NotBlank
    var city: String? = "",

    @NotBlank
    var country: String? = "",

    var postalCode: Int? = null,

    @OneToOne(mappedBy = "address", fetch = FetchType.LAZY)
    var customer: Customer? = null

)