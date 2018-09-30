# Kotlin-Extension
[![ex][exsvg]][ex]    
This library support these extensions, which are below:
- [Resource Id](https://github.com/nb312/Kotlin-Extension/blob/master/doc/ResourceID.md)
- [SharePreference](https://github.com/nb312/Kotlin-Extension/blob/master/doc/SharePreference.md)
- [Activity](https://github.com/nb312/Kotlin-Extension/blob/master/doc/Activity.md)
- [EventBus](https://github.com/nb312/Kotlin-Extension/blob/master/doc/EventBus.md)
- [TextView](https://github.com/nb312/Kotlin-Extension/blob/master/doc/TextView.md)
- [IO & File](https://github.com/nb312/Kotlin-Extension/blob/master/doc/IOFIle.md)
- [AES && RSA Encryption](https://github.com/nb312/Kotlin-Extension/blob/master/doc/Encryption.md)  

# SharedPreferences Example 
The Extension is convenient to use ,we just look at how to  call the SharePreference example,for the other examples ,you can click the link above to look their detail information. 
### Obtain the content with your key.
```kotlin
        "key1".getSPString()
        "key2".getSPBoolean()
        "key3".getSPFloat()
        "key4".getSPInt()
        "key5".getSPLong()
```
### save content with your key
```kotlin
        "key1".putSPString("Hello")
        "key2".putSPBoolean(false)
        "key3".putSPFloat(1.2f)
        "key4".putSPInt(12)
        "key5".putSPLong(2323L)
```
So easy!
# How to Start!

### 1. Add an URL of the repository 
In the build.gradle of the project root. Add code as following:
```gradle
allprojects {
    repositories {
        //others 
        maven {
            url "https://jitpack.io"
        }
       
    }
}
```
### 2. dependencies
In the `build.gradle` of the using module.
```kotlin
dependencies {
//others
     implementation 'com.github.nb312:Kotlin-Extension:0.0.6'
}
```
### 3. init 
You must init the param in your application: 
```kotlin
  NBExtensionUtil.context = this
```
Then you can use them happy.






[exsvg]:https://img.shields.io/badge/Kotlin--Extension-0.0.8-brightgreen.svg
[ex]:https://github.com/nb312/Kotlin-Extension
