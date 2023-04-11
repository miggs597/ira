package com.worldmarket.dao

import com.worldmarket.dao.DatabaseFactory.dbQuery
import com.worldmarket.models.CustomerModel.*
import com.worldmarket.models.OfferModel.*
import com.worldmarket.models.SpendModel.*
import org.jetbrains.exposed.sql.and
import java.time.LocalDateTime

class DAOFacadeImpl : DAOFacade {
    override suspend fun readCustomer(memberId: String): Customer? = dbQuery {
        Customer.find {
            Customers.memberId eq memberId
        }.singleOrNull()
    }

    override suspend fun readMemberSpend(memberId: String): MemberSpend? {
        return MemberSpend.find {
            MemberSpends.memberId eq memberId
        }.maxByOrNull { it.id }
    }

    override suspend fun readActiveRewards(memberId: String, benefit: String): List<OfferStatus> {
        return OfferStatus.find {
            (OfferStatuses.memberId eq memberId) and
                    (OfferStatuses.offerStatus eq "pending") and
                    (OfferStatuses.endDate greaterEq LocalDateTime.now())
        }.toList()
    }
}

val dao: DAOFacade = DAOFacadeImpl()