package com.worldmarket

import com.worldmarket.messages.Message.*
import com.worldmarket.messages.MessageConfig
import org.jetbrains.skia.*
import java.io.IOException
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
@Throws(IOException::class, NullPointerException::class)
fun getImage(
    messageConfig: MessageConfig,
    width: Int = 800,
    height: Int = 250
): ByteArray {
    val text = createTextForImage(messageConfig)

    val surface = Surface.makeRasterN32Premul(width, height)
    val canvas = surface.canvas

    val paint = Paint().apply {
        color = Color4f(255F, 0F, 0F).toColor()
    }

    val font = Font().apply {
        edging = FontEdging.SUBPIXEL_ANTI_ALIAS
        hinting = FontHinting.FULL
        size = 60F
    }

    val fontMetrics = font.measureText(text, paint)
    val x = (width - fontMetrics.width) / 2
    val y = height / 2F

    canvas.clear(Color.WHITE)
    canvas.drawString(text, x, y, font, paint)

    val data = surface.makeImageSnapshot().encodeToData() ?: throw NullPointerException()

    return data.bytes
}