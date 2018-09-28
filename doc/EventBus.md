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
