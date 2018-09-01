import java.io.Serializable

interface Clickable {
    fun click()
    fun showOff() = println("Im clickable!")
}
interface Focusable {
    fun setFocus(b:Boolean) =
            println("I ${if(b) "got" else "lost"} focus.")
    fun showOff() = println("Im forcesed")
}

class Button:Clickable,Focusable{  // 同名のデフォルト実装を持つインタエースを継承する事ができる
    override fun click() = println("Button clicked.")
    override fun showOff() {
        super<Clickable>.showOff()  // 親を指定してsuper呼び出し
        super<Focusable>.showOff()
    }
}

open class RichButton:Clickable{
    fun disable() {}  // デフォルト final なので上書きできない
    open fun animated() {} // openだから上書きできる
    override fun click() {} // overrideはopen扱い
}

abstract class Animated{
    abstract fun animate()
    open fun stopAnimating(){}
    fun animateTwice(){}
}

// final
// open
// abstract
// override (デフォルトopen)

fun main(args: Array<String>) {
    val button =Button()
    button.click()
    button.showOff()
    button.setFocus(true)
}

// 4.1.3
internal open class TalkativeButton:Focusable{
    private fun yell() = println("Hey!")
    protected fun whisper() = println("Lets talk!")
}

//fun TalkativeButton.giveSpeech(){
//    yell()
//    whisper()
//}

interface State:Serializable

interface View{
    fun getCurrentState():State
    fun restoreState(s:State){}
}
