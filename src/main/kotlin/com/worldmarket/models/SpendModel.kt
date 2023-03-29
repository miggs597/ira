package com.worldmarket.models

import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable

class SpendModel {
    object MemberSpends : IntIdTable("MemberSpend") {
        val memberId = varchar("member_id", 15)
        val postedPoints = varchar("posted_points", 12)
    }

    class MemberSpend(id: EntityID<Int>) : IntEntity(id) {
        companion object : IntEntityClass<MemberSpend>(MemberSpends)

        val memberId by MemberSpends.memberId
        val postedPoints by MemberSpends.postedPoints
    }
}