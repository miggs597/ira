package com.worldmarket

import com.worldmarket.dao.dao
import com.worldmarket.messages.Message.*
import com.worldmarket.messages.MessageConfigImpl
import com.worldmarket.models.CustomerModel.Customer
import kotlin.jvm.Throws

@Throws
suspend fun memberStatusImage(customer: Customer): ByteArray {
    if (!customer.profileCompleted) {
        return getImage(MessageConfigImpl(message = INCOMPLETE))
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
        )
    }

    val activeRewards = dao.readActiveRewards(customer.memberId, offer)

    if (activeRewards.isNotEmpty()) {
        return getImage(
            MessageConfigImpl(
                message = COUNT,
                count = activeRewards.size
            )
        )
    }

    return if (postedPoints == 0) {
        getImage(
            MessageConfigImpl(
                message = NOREWARD,
                offer = offer,
                pointsThreshold = pointsThreshold
            )
        )
    } else {
        getImage(
            MessageConfigImpl(
                message = POINTS,
                offer = offer
            )
        )
    }
}