package com.nb.example

import com.nb.ktextension.encrypto.decodeRSA
import com.nb.ktextension.encrypto.encodeRSA
import com.nb.ktextension.encrypto.genPriPubKeyHex
import org.junit.Test

/**
 * Created by NieBin on 18-9-30
 * Github: https://github.com/nb312
 * Email: niebin312@gmail.com
 */

class TestEncryption {
    @Test
    fun encryptionAes() {
        val (priHex, pubHex) = 2048.genPriPubKeyHex
        println("pr")
        val encode = "Hello world".encodeRSA(pubHex)
        val content = encode.decodeRSA(priHex)
        println("content:$content")
    }
}