package com.nb.ktextension.encrypto

import java.security.SecureRandom
import java.security.spec.KeySpec
import java.util.*
import javax.crypto.Cipher
import javax.crypto.SecretKey
import javax.crypto.SecretKeyFactory
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.PBEKeySpec
import javax.crypto.spec.SecretKeySpec

/**
 * Created by NieBin on 18-9-30
 * Github: https://github.com/nb312
 * Email: niebin312@gmail.com
 */

/**AES 算法*/
private const val AES = "AES"
private const val AES_CTR_NOPADDING = "AES/CTR/NoPadding"
private const val KEYFACTORY_SHA = "PBKDF2WithHmacSHA1"
private const val ALPHA_NUM = "abcdefghijklmnopqrstuvwxyz0987654321QWERTYUIOPASDFGHJKLZXCVBNM"

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


/**
 * 根据相应的key,加密指定数据
 * @param keyBytes key 的二进制数
 * */
fun ByteArray.encodeAes(keyBytes: ByteArray, ivParamBytes: ByteArray): ByteArray {
    var key: SecretKey = keyBytes.aesKey
    var cipher = Cipher.getInstance(AES_CTR_NOPADDING)
    cipher.init(Cipher.ENCRYPT_MODE, key, ivParamBytes.ivParamSpec)
    return cipher.doFinal(this)
}

/**
 * 根据相应的key,加密指定数据
 * @param keyBytes key 的二进制数
 * */
fun String.encodeAes(keyBytes: ByteArray, ivParamBytes: ByteArray): ByteArray {
    return this.toByteArray().encodeAes(keyBytes, ivParamBytes)
}

/**
 * 根据相应的key,加密指定数据
 * @param keyHex key 的16进制数
 * @param ivHex iv 的16进制数
 * */
fun ByteArray.encodeAes(keyHex: String, ivHex: String): ByteArray {
    return encodeAes(keyHex.hex2Array(), ivHex.hex2Array())
}

/**
 * 根据相应的key,加密指定数据
 * @param keyHex key 的16进制数
 * @param ivHex iv 的16进制数
 * */
fun String.encodeAes(keyHex: String, ivHex: String): ByteArray {
    return encodeAes(keyHex.hex2Array(), ivHex.hex2Array())
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
    return this.hex2Array().decodeAes(keyBytes, ivParamBytes)
}

/**密文根据AES 算法进行解密
 * @param keyHex secret key 的hex .
 * @param ivHex IvParameterSpec 的hex.
 * */
fun ByteArray.decodeAes(keyHex: String, ivHex: String): String {
    return decodeAes(keyHex.hex2Array(), ivHex.hex2Array())
}

/**
 * 密文根据AES 算法进行解密
 * @param keyHex secret key 的 hex .
 * @param ivHex IvParameterSpec 的hex.
 * */
fun String.decodeAes(keyHex: String, ivHex: String): String {
    return decodeAes(keyHex.hex2Array(), ivHex.hex2Array())
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

