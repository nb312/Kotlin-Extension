package com.nb.ktextension.encrypto

import java.security.*
import java.security.spec.PKCS8EncodedKeySpec
import java.security.spec.X509EncodedKeySpec
import javax.crypto.Cipher
import kotlin.math.absoluteValue

/**
 * Created by NieBin on 18-9-30
 * Github: https://github.com/nb312
 * Email: niebin312@gmail.com
 */


/**RSA 算法*/
private const val RSA = "RSA"
private const val RSA_NONE_PKCS1PADDING = "RSA/NONE/PKCS1PADDING"


/**根据 public key 加密给定数据
 * @param pubKeyBytes  public_key
 * */
fun ByteArray.encodeRSA(pubKeyBytes: ByteArray): ByteArray {
    var pubKey = pubKeyBytes.rsaPubKey
    var cipher = Cipher.getInstance(RSA_NONE_PKCS1PADDING) //or try with "RSA"
    cipher.init(Cipher.ENCRYPT_MODE, pubKey, SecureRandom())
    return cipher.doFinal(this)
}

/**
 * 根据 public key ,使用rsa算法加密给定数据
 * @param pubKeyBytes  public_key
 * */
fun String.encodeRSA(pubKeyBytes: ByteArray): ByteArray {
    return this.toByteArray().encodeRSA(pubKeyBytes)
}

/**根据 public key 加密给定数据
 * @param pubKeyHex the hex of  public_key
 * */
fun ByteArray.encodeRSA(pubKeyHex: String): ByteArray {
    return encodeRSA(pubKeyHex.hex2Array())
}

/**
 * 根据 public key ,使用rsa算法加密给定数据
 * @param pubKeyHex  hex of public_key
 * */
fun String.encodeRSA(pubKeyHex: String): ByteArray {
    return encodeRSA(pubKeyHex.hex2Array())
}

/**
 * 给定私钥解密byte array密文
 * @param priKeyBytes 私钥
 * */
fun ByteArray.decodeRSA(priKeyBytes: ByteArray): String {
    val privateKey = priKeyBytes.rsaPriKey
    var cipher = Cipher.getInstance(RSA_NONE_PKCS1PADDING) //or try with "RSA"
    cipher.init(Cipher.DECRYPT_MODE, privateKey, SecureRandom())
    return cipher.doFinal(this).toString(Charsets.UTF_8)
}

/**
 * 给定私钥解密 16进制密文
 * @param priKeyBytes 私钥
 * */
fun String.decodeRSA(priKeyBytes: ByteArray): String {
    return this.hex2Array().decodeRSA(priKeyBytes)
}

/**
 * 给定私钥解密byte array密文
 * @param priKeyHex 私钥
 * */
fun ByteArray.decodeRSA(priKeyHex: String): String {
    return decodeRSA(priKeyHex.hex2Array())
}

/**
 * 给定私钥解密 16进制密文
 * @param priKeyHex 私钥 the hex of private key.
 * */
fun String.decodeRSA(priKeyHex: String): String {
    return decodeRSA(priKeyHex.hex2Array())
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
/**
 * 获取 rsa的private key.
 * */
val String.rsaPriKey: PrivateKey
    get() = hex2Array().rsaPriKey
/**
 * 获取 rsa的public key.
 * */
val String.rsaPubKey: PublicKey
    get() = hex2Array().rsaPubKey

/**generate the key pair for rsa*/
val Int.genKeyPair: KeyPair
    get() {
        val generator = KeyPairGenerator.getInstance(RSA)
        generator.initialize(this.absoluteValue, SecureRandom())
        return generator.generateKeyPair()
    }
/**generate the private,public key for rsa */
val Int.genPriPubKeyBytes: Pair<ByteArray, ByteArray>
    get() {
        val key = genKeyPair
        return Pair(key.private.encoded, key.public.encoded)

    }
/**generate the private,public key for ras,the format is string.*/
val Int.genPriPubKeyHex: Pair<String, String>
    get() {
        val (pri, pub) = genPriPubKeyBytes
        return Pair(pri.array2Hex(), pub.array2Hex())
    }
