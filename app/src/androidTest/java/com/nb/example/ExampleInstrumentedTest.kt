package com.nb.example

import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import com.nb.ktextension.encrypto.array2Hex
import com.nb.ktextension.encrypto.decodeRSA
import com.nb.ktextension.encrypto.encodeRSA
import com.nb.ktextension.encrypto.genPriPubKeyHex

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getTargetContext()
        assertEquals("com.nb.example", appContext.packageName)
//        encryptionAes()
        decodeHex()
    }

    fun encryptionAes() {
        val (priHex, pubHex) = 256.genPriPubKeyHex
        println("pri:$priHex")
        println("pub: $pubHex")
        val encode = "Change the world!".encodeRSA(pubHex)
        val content = encode.decodeRSA(priHex)
        println("content:$content")
    }

    fun encodeHex() {
        val publicKey = "303c300d06092a864886f70d0101010500032b0030280221008ebcf4bbac6e81c41a81cbfa54382d1b3ea07325237581ba615e50df10780bdb0203010001"
        val encode = "Change the world!".encodeRSA(publicKey)
        println("encode: ${encode.array2Hex()}")
    }

    fun decodeHex() {
        val priHex = "3081c2020100300d06092a864886f70d01010105000481ad3081aa0201000221008ebcf4bbac6e81c41a81cbfa54382d1b3ea07325237581ba615e50df10780bdb02030100010220011da860208ca09bbd854ee89468a2aef52f826e1688cf73d6f62c6212a98fa1021100c1ddcbb92c5efa2642c39e9fb81776b1021100bc7c39b1c5567e97be53bc9e181a264b02107c8af6339193ba7415f401d56db8752102105ed6267562f46d47ce94f56f9a72f5d7021100a0d31867191f5969b008cfe3a7252b24"
        val encode = "177d814cf49cdc73ef3f73eee094d1fa2f9c88858d50fca715ecbb6b8efb8594"
        val content = encode.decodeRSA(priHex)
        println("content: $content")
    }
}
