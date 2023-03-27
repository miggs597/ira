package com.worldmarket.plugins

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.plugins.statuspages.*
import io.ktor.server.response.*

fun Application.configurePlugins() {
    install(StatusPages) {
        exception<Throwable> { call, cause ->
            when (cause) {
                is IllegalArgumentException -> call.respond(
                    HttpStatusCode.InternalServerError,
                    "memberID is not properly encoded as base64"
                )

                else -> call.respond(HttpStatusCode.InternalServerError, cause.localizedMessage)
            }
        }
    }
}