package com.worldmarket.models

import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.javatime.datetime

class OfferModel {
    object OfferStatuses : IntIdTable("MemberOfferStatus") {
        val memberId = varchar("member_id", 15)
        val benefitType = varchar("benefit_type", 100).nullable()
        val promocode = varchar("promocode", 100).nullable()
        val barcode = varchar("barcode", 100).nullable()
        val offerStatus = varchar("offer_status", 50).nullable()
        val heading = varchar("heading", 250).nullable()
        val endDate = datetime("usage_end_date").nullable()
    }

    class OfferStatus(id: EntityID<Int>) : IntEntity(id) {
        companion object : IntEntityClass<OfferStatus>(OfferStatuses)

        val memberId by OfferStatuses.memberId
        val benefitType by OfferStatuses.benefitType
        val promocode by OfferStatuses.promocode
        val barcode by OfferStatuses.barcode
        val offerStatus by OfferStatuses.offerStatus
        val heading by OfferStatuses.heading
        val endDate by OfferStatuses.endDate
    }
}