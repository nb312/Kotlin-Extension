# Kotlin-Extension
[![ex][exsvg]][ex]    
This library support these extensions, which are below:
- [Resource Id](https://github.com/nb312/Kotlin-Extension/blob/master/doc/ResourceID.md)
- [SharePreference](https://github.com/nb312/Kotlin-Extension/blob/master/doc/SharePreference.md)
- [Activity](https://github.com/nb312/Kotlin-Extension/blob/master/doc/Activity.md)
- [EventBus](https://github.com/nb312/Kotlin-Extension/blob/master/doc/EventBus.md)
- [TextView](https://github.com/nb312/Kotlin-Extension/blob/master/doc/TextView.md)
- IO & File
- Encryption

# Start

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




# SharedPreferences 
You can just use them with the key,value.Examples:
```kotlin
        var v = "your_key".getSPString() //get the value with the key 'your_key'
        v.print() // you can use "your_key1".getSPString("--").print(),but someone maybe confuse with it.
        "your_key".putSPString("Hello world") //set the value of the key 'your_key' to Hello world.
        "your_key".getSPString("--").print() //get the value and print,the default value is '--',you could not must put it at all.
```
Support the getting types are below: 
```kotlin
        "key1".getSPString()
        "key2".getSPBoolean()
        "key3".getSPFloat()
        "key4".getSPInt()
        "key5".getSPLong()
```
Putting types: 
```kotlin
        "key1".putSPString("Hello")
        "key2".putSPBoolean(false)
        "key3".putSPFloat(1.2f)
        "key4".putSPInt(12)
        "key5".putSPLong(2323L)
```
So easy!

[exsvg]:https://img.shields.io/badge/Kotlin--Extension-0.0.7-brightgreen.svg
[ex]:https://github.com/nb312/Kotlin-Extension
