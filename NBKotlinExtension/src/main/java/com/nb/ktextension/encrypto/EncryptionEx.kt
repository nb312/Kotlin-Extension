package com.nb.ktextension.encrypto

import java.security.KeyFactory
import java.security.PrivateKey
import java.security.PublicKey
import java.security.SecureRandom
import java.security.spec.PKCS8EncodedKeySpec
import java.security.spec.X509EncodedKeySpec
import javax.crypto.Cipher

/**
 * Created by NieBin on 18-9-26
 * Github: https://github.com/nb312
 * Email: niebin312@gmail.com
 */





const val digits = "0123456789abcdef"

/**
 * Return length many bytes of the passed in byte array as a hex string.
 *
 * @param data   the bytes to be converted.
 * @param length the number of bytes in the data block to be converted.
 * @return a hex representation of length bytes of data.
 */
fun ByteArray.toHex(length: Int = 0): String {
    val buf = StringBuffer()
    for (i in 0 until length) {
        val v = this[i].toInt() and 0xff
        buf.append(digits[v shr 4])
        buf.append(digits[v and 0xf])
    }
    return buf.toString()
}

/**
 * 转换为16进制的String
 * Return the passed in byte array as a hex string.
 *
 * @param data the bytes to be converted.
 * @return a hex representation of data.
 */
fun ByteArray.toHex(): String {
    return this.toHex(this.size)
}

/**
 * To byte array byte [ ].
 *
 * @param hexString the hex string
 * @return the byte [ ]
 */
fun String.toByteArray16(): ByteArray {
    var hexString = this
    hexString = hexString.toLowerCase()
    val byteArray = ByteArray(hexString.length shr 1)
    var index = 0
    for (i in 0 until hexString.length) {
        if (index > hexString.length - 1)
            return byteArray
        val highDit = (Character.digit(hexString[index], 16) and 0xFF)
        val lowDit = (Character.digit(hexString[index + 1], 16) and 0xFF)
        byteArray[i] = (highDit shl 4 or lowDit).toByte()
        index += 2
    }
    return byteArray
}

