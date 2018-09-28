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
