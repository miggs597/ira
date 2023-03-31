package com.worldmarket

import com.worldmarket.messages.Message.*
import com.worldmarket.messages.MessageConfig
import java.awt.Color
import java.awt.Font
import java.awt.RenderingHints
import java.awt.image.BufferedImage
import java.io.ByteArrayOutputStream
import javax.imageio.ImageIO
import kotlin.jvm.Throws

/**
 * Text for the image
 *
 * @param messageConfig Determines what text to return
 */
private fun createTextForImage(
    messageConfig: MessageConfig
): String = when (messageConfig.message) {
    COUNT -> "You have ${messageConfig.count} $5 SHOPPER REWARD[s] waiting! GET REWARD >"
    INCOMPLETE -> "Want a birthday surprise? COMPLETE PROFILE >"
    POINTS -> "Just ${messageConfig.points} more points for a $5 SHOPPER REWARD SIGN IN >"
    INVALID -> "Invalid Member ID Entered"
    NOREWARD -> "Get ${messageConfig.offer} for every ${messageConfig.pointsThreshold} earned! SIGN IN >"
}

/**
 * Renders images intended for emails.
 *
 * @param messageConfig Used to determine what text to build
 * @param width of the image
 * @param height of the image
 * @return PNG ByteArray
 */
@Throws
fun getImage(
    messageConfig: MessageConfig,
    width: Int = 800,
    height: Int = 250
): ByteArray {
    val text = createTextForImage(messageConfig)
    val image = BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB)
    val g2d = image.createGraphics()

    g2d.font = Font("Serif", Font.BOLD, 24)
    g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON)
    g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON)
    g2d.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON)
    g2d.color = Color.BLACK

    val textWidth = g2d.fontMetrics.stringWidth(text)

    g2d.drawString(text, (width - textWidth) / 2, height / 2)
    g2d.dispose()

    val baos = ByteArrayOutputStream()
    ImageIO.write(image, "png", baos)

    return baos.toByteArray()
}