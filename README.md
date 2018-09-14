# Kotlin-Extension
[![ex][exsvg]][ex]    
This library have the extensions are below:
- Resource Id 
- SharePreference 
- Activity
- EventBus 
- TextView 

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


# Resource Id
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

# Activity
### No data 
If you want start an activity,like `MainActivity` with no data,just below:
```kotlin
activity.startAct(MainActivity::class.java)
```
Or 
```kotlin
context.startAct(MainActivity::class.java)
```
if you call it in an Activity,codes are like this: 
```kotlin
 startAct(MainActivity::class.java)
```
It's cool.
### With data
It support the three ways like above,just different with its data.Example is below:
**put the data**
```kotlin
 var mainData = MainData("Hello world") //the MainData is defined by you,but make sure the MainData implement the Serializable interface.
 startAct(MainActivity::class.java, mainData) 
```
Then **get the data** with `Intent` or `activity`,they are like these `intent.getCurrentData()` and `activity.getCurrentData()`,if call in an activity just call `getCurrentData()`.Example is below:
```kotlin
   var mainData: MainData = getCurrentData()
```
But you should be careful to make sure the 'activity' or 'intent' have the data,if not,there will be an error throwed.

# EventBus
**Register the EventBus**  
When you want to register the EventBus to an object,you can just call it in its function,like below: 
```kotlin 
registerEventBus()
```
**Cancel the EventBus**:
```kotlin
unEventBus()
```
**Send Message**:
1. define a data item for event
```kotlin
class MainEvent(var data: String = "") : NBaseEvent() {
    init {
        eventType = TYPE_INIT
    }

    companion object {
        /**initialize the main activity*/
        const val TYPE_INIT = 0x101
        /**refresh the data*/
        const val TYPE_REFRESH = 0x102
        /**finish the activity*/
        const val TYPE_FINISH = 0x103
    }
}
```
You must extend the class `NBaseEvent`,the part of the `  companion object`  or the `data` variable is not necessary,if you have only one feature.Just like this `class MainEvent:NBaseEvent()`,then you can send it.  
2. send 
```
var mainEvent = MainEvent("say hello world")
mainEvent.sendEBusMessage()
```
**Receive message**
1. define a interface   
This interface can use in many place,if you want to accept the `MainEvent` with different types:
```kotlin
interface IMainEvent {
    fun onMainEvent(event: MainEvent)
}
```
2. implement
```kotlin
 @Subscribe(threadMode = ThreadMode.MAIN)
    override fun onMainEvent(event: MainEvent) {
        when (event.eventType) {
            MainEvent.TYPE_INIT -> "init activity".print()
            MainEvent.TYPE_REFRESH -> "refresh data".print()
            MainEvent.TYPE_FINISH -> "finish MainActivity".print()
        }
    }
```

# TextView 
Get the string
```kotlin
var content=textView.trimStr
```
Change the state from to text to password type:
```
textView.change2Pwd(true)
```
only see the afterChanged function: 
```kotlin
textView.afterChange {
            "only see the afterTextChanged function".print()
        }
```
See all with one function:
```koltin
textView.afterChange {
            "only see the afterTextChanged function".print()
        }
```
Check some text view have empty content:
```kotlin
var isE=  mutableListOf(textView1, textView2).hasOneEmpty() //true means there is at least one textview has empty content.
```



[exsvg]:https://img.shields.io/badge/Kotlin--Extension-0.0.6-brightgreen.svg
[ex]:https://github.com/nb312/Kotlin-Extension
