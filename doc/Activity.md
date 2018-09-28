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
