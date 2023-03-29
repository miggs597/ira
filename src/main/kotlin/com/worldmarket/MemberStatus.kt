package com.worldmarket

import com.worldmarket.dao.dao
import com.worldmarket.models.CustomerModel.Customer
import io.ktor.http.*
import java.io.ByteArrayOutputStream
import kotlin.jvm.Throws

@Throws
suspend fun memberStatusImage(customer: Customer?): Pair<ByteArray, HttpStatusCode> {
    if (customer == null) {
        throw NullPointerException()
    }

    if (!customer.profileCompleted) {
        return getImage(Message.INCOMPLETE) to HttpStatusCode.OK
    }

    val memberSpend = dao.readMemberSpend(customer.memberId)




    return ByteArrayOutputStream().toByteArray() to HttpStatusCode.OK
}