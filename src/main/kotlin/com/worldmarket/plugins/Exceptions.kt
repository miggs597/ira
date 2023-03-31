package com.worldmarket.plugins

import com.worldmarket.messages.Message.*
import com.worldmarket.getImage
import com.worldmarket.messages.MessageConfigImpl
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.plugins.statuspages.*
import io.ktor.server.response.*

/**
 * Catches thrown exceptions
 */
fun Application.configurePlugins() {
    install(StatusPages) {
        exception<Throwable> { call, cause ->
            when (cause) {
                is IllegalArgumentException -> call.respondBytes(
                    getImage(MessageConfigImpl(message = INVALID)),
                    ContentType.Image.PNG,
                    HttpStatusCode.InternalServerError,
                )

                else -> call.respond(HttpStatusCode.InternalServerError, cause.localizedMessage)
            }
        }
    }
}