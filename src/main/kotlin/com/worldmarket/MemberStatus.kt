package com.worldmarket

import com.worldmarket.dao.dao
import com.worldmarket.messages.Message.*
import com.worldmarket.messages.MessageConfigImpl
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
        return getImage(MessageConfigImpl(message = INCOMPLETE)) to HttpStatusCode.OK
    }

    val postedPoints = dao.readMemberSpend(customer.memberId)
        ?.postedPoints
        ?.toIntOrNull()

    val offer = System.getenv("offer") ?: "$5 SHOPPER REWARD"
    val pointsThreshold = System.getenv("pointsThreshold") ?: "500"

    if (postedPoints == null) {
        return getImage(
            MessageConfigImpl(
                message = NOREWARD,
                offer = offer,
                pointsThreshold = pointsThreshold
            )
        ) to HttpStatusCode.OK
    }

    return ByteArrayOutputStream().toByteArray() to HttpStatusCode.OK
}