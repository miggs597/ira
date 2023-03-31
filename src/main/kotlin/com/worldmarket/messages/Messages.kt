package com.worldmarket.messages

enum class Message {
    COUNT, INCOMPLETE, POINTS, INVALID, NOREWARD
}

interface MessageConfig {
    val message: Message
    val count: Int
    val points: Int
    val offer: String
    val pointsThreshold: String
}

data class MessageConfigImpl(
    override val message: Message,
    override val count: Int = 0,
    override val points: Int = 0,
    override val offer: String = "",
    override val pointsThreshold: String = "500"
) : MessageConfig