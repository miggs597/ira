package com.worldmarket.plugins

import com.worldmarket.dao.dao
import com.worldmarket.memberStatusImage
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import java.util.*

fun Application.configureRouting() {
    routing {
        get("/") {
            call.respond("Welcome to the IRA")
        }

        get("/shopper-reward-status") {
            val encodedMemberID = call.parameters["code"] ?: throw IllegalArgumentException()

            val memberID = Base64.getDecoder().decode(encodedMemberID).decodeToString()
            val customer = dao.readCustomer(memberID)
            val (imageByteArray, statusCode) = memberStatusImage(customer)

            call.respondBytes(imageByteArray, ContentType.Image.PNG, statusCode)
        }
    }
}
