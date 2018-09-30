# Encryption 
In this library,we just support two algorithm.**AES**,**RSA**.

# AES
If you create the key by yourself,the example is below:
- ### encode with generate key.
```kotlin
  var (encode, key, iv) = "Hello World!".encodeAesWithLength(keyLen = 256, ivParamLen = 16)
  println("encode:${encode.toHex()} ")
  println("key:${key.toHex()}")
  println("iv:${iv.toHex()}").
```
The results of three main params with hex type are below: 

| Param | Value |   
| --- | --- |  
| encode | be53a1e99e14c1ccafa38fa6 |  
| key | 20b6b92d78cf7c1ada2ac415bcc6d13e4974337d35e9499934a20af17d749509 |
| iv | 48993cc0c7ecee4badd0c98691413922 |

**encode** is an byte array of the encode string.  
**key** is an byte array of the sercret key.you need to save it for decoding your encode content,else for **iv** .   
The params of encodeAesWithLength could not necessary to input,if not ,it will use its default value,the keyLen default of 256 and 
ivParamLen default of 16.  

The example above is use the data directly,if you want to use the key with the hex format.
- ### encode with hex string of the exist key and iv  
```kotlin
val key = "20b6b92d78cf7c1ada2ac415bcc6d13e4974337d35e9499934a20af17d749509".toByteArray16()
val iv = "48993cc0c7ecee4badd0c98691413922".toByteArray16()
val encode = "Hello World for all of you".encodeAes(key, iv)
val content = encode.decodeAes(key, iv) // the hex of encode is 'be53a1e99e14c1ccafa38fa77d8513c47e192e9ee6f086dca399'
println("content: $content") // print 'content: Hello World for all of you'
```
The ciphertext is _be53a1e99e14c1ccafa38fa77d8513c47e192e9ee6f086dca399_
- ### decode with byte array
Above,we already use the decode function to decode the content,
```kotlin
 var content = encode.decodeAes(key, iv)
 println("content: $content") //print 'content: Hello World for all of you'
```
- ### decode with hex string.
if you use want use the hex of the ciphertext, just like this: 
```kotlin
val content = "be53a1e99e14c1ccafa38fa77d8513c47e192e9ee6f086dca399".decodeAes(key, iv)
println("content: $content") // print 'content: Hello World for all of you'
```
# RSA 
- ### How to generate key.
Generate the hex format of the private,public key. 
```kotlin
val (priHex, pubHex) = 256.genPriPubKeyHex // be careful that the private key is front of public key.Recommend the length is more than 2028. 
```
The key will generate as below.  

| Key | value | 
| --- | --- |
| private key |  3081c2020100300d06092a864886f70d01010105000481ad3081aa0201000221008ebcf4bbac6e81c41a81cbfa54382d1b3ea07325237581ba615e50df10780bdb02030100010220011da860208ca09bbd854ee89468a2aef52f826e1688cf73d6f62c6212a98fa1021100c1ddcbb92c5efa2642c39e9fb81776b1021100bc7c39b1c5567e97be53bc9e181a264b02107c8af6339193ba7415f401d56db8752102105ed6267562f46d47ce94f56f9a72f5d7021100a0d31867191f5969b008cfe3a7252b24 |
 | public key | 303c300d06092a864886f70d0101010500032b0030280221008ebcf4bbac6e81c41a81cbfa54382d1b3ea07325237581ba615e50df10780bdb0203010001
 |
  
- ### encode the String
if you want to encode a string or byte array.you can just like this.
```kotlin
   val publicKey = "303c300d06092a864886f70d0101010500032b0030280221008ebcf4bbac6e81c41a81cbfa54382d1b3ea07325237581ba615e50df10780bdb0203010001"
   val encode = "Change the world!".encodeRSA(publicKey)
   println("encode: ${encode.array2Hex()}")
```
The Ciphertext is _177d814cf49cdc73ef3f73eee094d1fa2f9c88858d50fca715ecbb6b8efb8594_

- ### Decode the ciphertext 
```kotlin
val priHex = "3081c2020100300d06092a864886f70d01010105000481ad3081aa0201000221008ebcf4bbac6e81c41a81cbfa54382d1b3ea07325237581ba615e50df10780bdb02030100010220011da860208ca09bbd854ee89468a2aef52f826e1688cf73d6f62c6212a98fa1021100c1ddcbb92c5efa2642c39e9fb81776b1021100bc7c39b1c5567e97be53bc9e181a264b02107c8af6339193ba7415f401d56db8752102105ed6267562f46d47ce94f56f9a72f5d7021100a0d31867191f5969b008cfe3a7252b24"
val encode = "177d814cf49cdc73ef3f73eee094d1fa2f9c88858d50fca715ecbb6b8efb8594"
val content = encode.decodeRSA(priHex)
println("content: $content")
```
Will print the 'content: Change the world!'

It is easy to use.

# You should know
In the examples above, wo just use the key,content with string format, but you can use the byte array as well. the functions name is the same as the up functions.

