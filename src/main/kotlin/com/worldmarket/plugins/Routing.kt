package com.worldmarket.plugins

import com.worldmarket.dao.dao
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import java.util.*

fun Application.configureRouting() {
    routing {
        get("/") {
            call.respondText("Hello World!")
        }

        get("/shopper-reward-status") {
            val encodedMemberID = call.parameters["code"]

            if (encodedMemberID == null) {
                call.respond(HttpStatusCode.NotFound, "No memberID provided")
            }

            val memberID = Base64.getDecoder().decode(encodedMemberID).decodeToString()

            call.respond(memberID)
        }

        get("/member/{memberId}") {
            val memberId = call.parameters["memberId"] ?: ""

            call.respond(dao.readCustomer(memberId)?.firstName ?: "Nothing is here")
        }
    }
}
