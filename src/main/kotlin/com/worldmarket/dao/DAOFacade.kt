package com.worldmarket.dao

import com.worldmarket.models.CustomerModel.*
import com.worldmarket.models.OfferModel.*
import com.worldmarket.models.SpendModel.*

interface DAOFacade {
    suspend fun readCustomer(memberId: String): Customer?
    suspend fun readOffer(): OfferStatus?
    suspend fun readMemberSpend(memberId: String): MemberSpend?
}