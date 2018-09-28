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
