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
- ### decode with byte array
Above,we already use the decode function to decode the content,
```kotlin
 var content = encode.decodeAes(key, iv)
 println("content: $content") //print 'content: Hello World for all of you'
```
- ### decode with hex string.
if you use want use the hex of the encode content, just like this: 
```kotlin
val content = "be53a1e99e14c1ccafa38fa77d8513c47e192e9ee6f086dca399".decodeAes(key, iv)
println("content: $content") // print 'content: Hello World for all of you'
```


