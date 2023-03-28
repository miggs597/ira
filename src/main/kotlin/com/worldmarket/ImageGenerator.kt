package com.worldmarket

import java.awt.Color
import java.awt.Font
import java.awt.RenderingHints
import java.awt.image.BufferedImage
import java.io.ByteArrayOutputStream
import javax.imageio.ImageIO
import kotlin.jvm.Throws

enum class Message {
    COUNT, INCOMPLETE, POINTS
}

fun createTextForImage(
    message: Message,
    count: Int? = null,
    points: Int? = null
): String = when (message) {
    Message.COUNT -> "You have ${count ?: "a"} $5 SHOPPER REWARD waiting! GET REWARD >"
    Message.INCOMPLETE -> "Want a birthday surprise? COMPLETE PROFILE >"
    Message.POINTS -> "Just ${points ?: 0} more points for a $5 SHOPPER REWARD SIGN IN >"
}

@Throws
fun getImage(message: Message, width: Int = 800, height: Int = 250): ByteArray {

    val text = createTextForImage(message)
    val image = BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB)
    val g2d = image.createGraphics()

    g2d.font = Font("Serif", Font.BOLD, 24)
    g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
    g2d.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);
    g2d.color = Color.BLACK

    val fontMetrics = g2d.fontMetrics
    val textWidth = fontMetrics.stringWidth(text)

    g2d.drawString(text, (width - textWidth) / 2,height / 2)
    g2d.dispose()

    val baos = ByteArrayOutputStream()
    ImageIO.write(image, "png", baos)

    return baos.toByteArray()
}