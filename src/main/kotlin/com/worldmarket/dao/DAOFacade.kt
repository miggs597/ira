package com.worldmarket.dao

import com.worldmarket.models.CustomerModel
import com.worldmarket.models.OfferModel

interface DAOFacade {
    suspend fun readCustomer(memberId: String): CustomerModel.Customer?
    suspend fun readOffer(): OfferModel.OfferStatus?
}