# KotlinCommonUtil
This library is writed by Koltin.When I was developing,I have some code with easy using,I just put them here.
Expecially, I think the extension of using within kotlin is very convinence.I use so many extension code in  this library.
# Id extension
Normally,if you do not code in the activity or fragment,we may use the id like this.
```kotlin
        var hello = context.resources.getString(R.string.hello)
        var green = context.resources.getColor(R.color.green)
        var fruit = context.resources.getStringArray(R.array.fruit)
        
        Toast.makeText(context, "Hello world", Toast.LENGTH_SHORT).show()
        Toast.makeText(context, R.string.hello, Toast.LENGTH_SHORT).show()
        
        print("Hello world")
        print(context.resources.getString(R.string.hello))
```
But,with this library,you can use them like beneath.
```kotlin
        var hello = R.string.hello.id2String //obtain the 'Hello world'
        var green = R.color.green.id2Color // 'obtain the color green of your define'
        var fruit = R.array.fruit.id2StrList // obtain the array and convert to mutableList

        "Hello world".toast() // toast 'Hello world'
        R.string.hello.id2Toast() //toast 'Hello world'

        "Hello world".print() // print 'Hello world'
        R.string.hello.print() //print 'Hello world'
        "TAG".print("Hello word") // print 'TAG: Hello World'
        objectPrint("Hello world") // it depend on your current calling class, if it's MainActivity then will print `MainActivity: Hello World`
```
Are you surprise to this?It's sample and easy to use.   
If you want get the string from the clipboard,just use like following: 
```kotlin
var clipString=getClipString()
```
And **copy** the string to the clipboard,so easy:
```kotlin 
"Hello world".copy2Board() 
```
Above,it is so cool to use the string extension,if you have some idea about these alike way, you can upload them on this project.

# SharedPreferences Extension 
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


