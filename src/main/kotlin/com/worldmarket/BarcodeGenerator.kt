package com.worldmarket

import com.google.zxing.BarcodeFormat
import com.google.zxing.MultiFormatWriter
import com.google.zxing.WriterException
import com.google.zxing.client.j2se.MatrixToImageWriter
import java.io.ByteArrayOutputStream
import java.io.IOException

/**
 * Creates a barcode
 * @param barcode String used to create a CODE_128 barcode
 * @return ByteArray
 */
@Throws(IOException::class, WriterException::class)
fun getBarcode(barcode: String): ByteArray {
    val bitMatrix = MultiFormatWriter().encode(barcode, BarcodeFormat.CODE_128, 500, 250)
    val baos = ByteArrayOutputStream()

    MatrixToImageWriter.writeToStream(bitMatrix, "png", baos)

    return baos.toByteArray()
}