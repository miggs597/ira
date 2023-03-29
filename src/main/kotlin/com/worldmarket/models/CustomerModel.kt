package com.worldmarket.models

import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.javatime.datetime

class CustomerModel {
    object Customers : IntIdTable("loyaltylab_customer") {
        val memberId = varchar("member_id", 15)
        val firstName = varchar("first_name", 256).nullable()
        val lastName = varchar("last_name", 256).nullable()
        val email = varchar("email", 254).nullable()
        val memberSince = datetime("member_since").nullable()
        val accountStatus = bool("account_Status")
        val unmarketable = bool("un_marketable").nullable()
        val profileCompleted = bool("profile_completed")
    }

    class Customer(id: EntityID<Int>) : IntEntity(id) {
        companion object : IntEntityClass<Customer>(Customers)

        val memberId by Customers.memberId
        val firstName by Customers.firstName
        val lastName by Customers.lastName
        val email by Customers.email
        val memberSince by Customers.memberSince
        val accountStatus by Customers.accountStatus
        val unmarketable by Customers.unmarketable
        val profileCompleted by Customers.profileCompleted
    }
}