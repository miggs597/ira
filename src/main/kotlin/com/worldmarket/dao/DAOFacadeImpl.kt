package com.worldmarket.dao

import com.worldmarket.dao.DatabaseFactory.dbQuery
import com.worldmarket.models.CustomerModel
import com.worldmarket.models.OfferModel

class DAOFacadeImpl : DAOFacade {
    override suspend fun readCustomer(memberId: String): CustomerModel.Customer? = dbQuery {
        CustomerModel.Customer.find {
            CustomerModel.Customers.memberId eq memberId
        }.singleOrNull()
    }

    override suspend fun readOffer(): OfferModel.OfferStatus? {
        TODO("Not yet implemented")
    }
}

val dao: DAOFacade = DAOFacadeImpl()