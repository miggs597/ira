package com.worldmarket.plugins

import com.worldmarket.Message
import com.worldmarket.dao.dao
import com.worldmarket.getImage
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
            val encodedMemberID = call.parameters["code"]

            if (encodedMemberID == null) {
                call.respond(HttpStatusCode.NotFound, "No memberID provided")
            }

            val memberID = Base64.getDecoder().decode(encodedMemberID).decodeToString()
            val customer = dao.readCustomer(memberID)

            if (customer == null) {
                call.respond(HttpStatusCode.NotFound)
            }

            call.respond(memberID)
        }

        get("/member/{memberId}") {
            val memberId = call.parameters["memberId"] ?: ""

            call.respond(dao.readCustomer(memberId)?.toString() ?: "Nothing is here")
        }
    }
}
