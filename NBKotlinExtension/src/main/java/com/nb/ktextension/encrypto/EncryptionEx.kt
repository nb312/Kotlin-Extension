package com.nb.ktextension.encrypto

import java.security.KeyFactory
import java.security.PrivateKey
import java.security.PublicKey
import java.security.SecureRandom
import java.security.spec.KeySpec
import java.security.spec.PKCS8EncodedKeySpec
import java.security.spec.X509EncodedKeySpec
import java.util.*
import javax.crypto.Cipher
import javax.crypto.SecretKey
import javax.crypto.SecretKeyFactory
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.PBEKeySpec
import javax.crypto.spec.SecretKeySpec

/**
 * Created by NieBin on 18-9-26
 * Github: https://github.com/nb312
 * Email: niebin312@gmail.com
 */


/**AES 算法*/
private const val AES = "AES"
private const val RSA = "RSA"
private const val AES_CTR_NOPADDING = "AES/CTR/NoPadding"
private const val RSA_NONE_PKCS1PADDING = "RSA/NONE/PKCS1PADDING"
private const val ALPHA_NUM = "abcdefghijklmnopqrstuvwxyz0987654321QWERTYUIOPASDFGHJKLZXCVBNM"
private const val KEYFACTORY_SHA = "PBKDF2WithHmacSHA1"


private val digits = "0123456789abcdef"

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

/**生成指定长度的随机数字，字母组合*/
val Int.randomLetters: String
    get() {
        var buf = StringBuffer()
        var len = ALPHA_NUM.length
        for (i in 0..this) {
            var ran = Random().nextInt(len)
            buf.append(ALPHA_NUM[ran])
        }
        return buf.toString()
    }

/**生成指定长度的 key*/
fun Int.generateAESKey(): SecretKey {
    var password = 10.randomLetters
    var iterationCount = 1000
    var keyLength = this
    var saltLength: Int = keyLength / 8 // same size as key output

    var random = SecureRandom()
    var salt = ByteArray(saltLength)
    random.nextBytes(salt)
    var keySpec: KeySpec = PBEKeySpec(password.toCharArray(), salt, iterationCount, keyLength)
    var keyFactory = SecretKeyFactory
            .getInstance(KEYFACTORY_SHA)
    var keyBytes: ByteArray = keyFactory.generateSecret(keySpec).encoded
    return SecretKeySpec(keyBytes, AES)
}

/**密文根据AES 算法进行解密
 * @param keyBytes secret key 的 byte array .
 * @param ivParamBytes IvParameterSpec 的byte array.
 * */
fun ByteArray.decodeAes(keyBytes: ByteArray, ivParamBytes: ByteArray): String {
    var key: SecretKey = keyBytes.aesKey
    var ivParams = ivParamBytes.ivParamSpec
    var cipher = Cipher.getInstance(AES_CTR_NOPADDING) //or try with "RSA"
    cipher.init(Cipher.DECRYPT_MODE, key, ivParams)
    val plaintext = cipher.doFinal(this)
    return plaintext.toString(Charsets.UTF_8)
}

/**
 * 密文根据AES 算法进行解密
 * @param keyBytes secret key 的 byte array .
 * @param ivParamBytes IvParameterSpec 的byte array.
 * */
fun String.decodeAes(keyBytes: ByteArray, ivParamBytes: ByteArray): String {
    return this.toByteArray16().decodeAes(keyBytes, ivParamBytes)
}

/**
 * 根据相应的key,加密指定数据
 * @param keyBytes key 的二进制数
 * */
fun ByteArray.encodeAes(keyBytes: ByteArray, ivParamBytes: ByteArray): ByteArray {
    var key: SecretKey = keyBytes.aesKey
    var cipher = Cipher.getInstance(AES_CTR_NOPADDING)
    cipher.init(Cipher.ENCRYPT_MODE, key, ivParamBytes.ivParamSpec)
    val cryptoByte = cipher.doFinal(this)
    return cryptoByte
}

/**
 * 根据相应的key,加密指定数据
 * @param keyBytes key 的二进制数
 * */
fun String.encodeAes(keyBytes: ByteArray, ivParamBytes: ByteArray): ByteArray {
    return this.toByteArray().encodeAes(keyBytes, ivParamBytes)
}

/**使用 以下数据进行
 * @param keyLen  key长度 ，默认256 位
 * @param ivParamLen IvParameterSpec随机数的长度，默认16 位
 * */
fun String.encodeAesWithLength(keyLen: Int = 256, ivParamLen: Int = 16): Triple<ByteArray, ByteArray, ByteArray> {
    var key = keyLen.generateAESKey()
    var ivParam = ivParamLen.ivParamSpec
    var encodeBytes = this.encodeAes(keyBytes = key.encoded, ivParamBytes = ivParam.iv)
    return Triple(encodeBytes, key.encoded, ivParam.iv)
}

/**根据 public key 加密给定数据
 * @param pubKeyBytes  public_key
 * */
fun ByteArray.encodeRSA(pubKeyBytes: ByteArray): Pair<ByteArray, PublicKey> {
    var pubKey = pubKeyBytes.rsaPubKey
    var cipher = Cipher.getInstance(RSA_NONE_PKCS1PADDING) //or try with "RSA"
    cipher.init(Cipher.ENCRYPT_MODE, pubKey, SecureRandom())
    var encode = cipher.doFinal(this)
    return Pair(encode, pubKey)
}

/**
 * 根据 public key ,使用rsa算法加密给定数据
 * @param pubKeyBytes  public_key
 * */
fun String.encodeRSA(pubKeyBytes: ByteArray): Pair<ByteArray, PublicKey> {
    return this.toByteArray().encodeRSA(pubKeyBytes)
}

/**
 * 给定私钥解密byte array密文
 * @param priKeyBytes 私钥
 * */
fun ByteArray.decodeRSA(priKeyBytes: ByteArray): Pair<String, PrivateKey> {
    val privateKey = priKeyBytes.rsaPriKey
    var cipher = Cipher.getInstance(RSA_NONE_PKCS1PADDING) //or try with "RSA"
    cipher.init(Cipher.DECRYPT_MODE, privateKey, SecureRandom())
    var content = cipher.doFinal(this).toString(Charsets.UTF_8)
    return Pair(content, privateKey)
}

/**
 * 给定私钥解密 16进制密文
 * @param priKeyBytes 私钥
 * */
fun String.decodeRSA(priKeyBytes: ByteArray): Pair<String, PrivateKey> {
    return this.toByteArray16().decodeRSA(priKeyBytes)
}

/**
 * 获取 rsa的private key.
 * */
val ByteArray.rsaPriKey: PrivateKey
    get() {
        val keyFactory = KeyFactory.getInstance(RSA)
        return keyFactory.generatePrivate(PKCS8EncodedKeySpec(this))
    }
/**
 * 获取 rsa的public key.
 * */
val ByteArray.rsaPubKey: PublicKey
    get() {
        val keyFactory = KeyFactory.getInstance(RSA)
        return keyFactory.generatePublic(X509EncodedKeySpec(this))
    }
/**获取AES 的SecretKey*/
val ByteArray.aesKey: SecretKeySpec
    get() = SecretKeySpec(this, AES)

/**就是IvParameterSpec*/
val ByteArray.ivParamSpec: IvParameterSpec
    get() {
        return IvParameterSpec(this)
    }

/**
 * 指定长度的 IvParameterSpec
 * */
val Int.ivParamSpec: IvParameterSpec
    get() {
        val ivBytes = ByteArray(this)
        // initially randomize
        SecureRandom().nextBytes(ivBytes)

        return IvParameterSpec(ivBytes)
    }
