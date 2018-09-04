import java.io.Serializable
import javax.management.Attribute
import javax.naming.Context

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

//fun main(args: Array<String>) {
//    val button =Button()
//    button.click()
//    button.showOff()
//    button.setFocus(true)
//}

// 4.1.3
internal open class TalkativeButton:Focusable{
    private fun yell() = println("Hey!")
    protected fun whisper() = println("Lets talk!")
}

//fun TalkativeButton.giveSpeech(){
//    yell()
//    whisper()
//}

//interface State:Serializable
//
//interface View{
//    fun getCurrentState():State
//    fun restoreState(s:State){}
//}

// 4.2
//class User(val nickname:String,
//           val isSubscribed:Boolean =true)
// プライマリコンストラクタと初期化ブロック、
// セカンダリコンストラクタ、
// まだ存在意義がわからない。
open class View{
    constructor(ctx: Context){
    }
    constructor(ctx:Context, attr: Attribute){

    }
}

class Button2:View{
    constructor(ctx:Context):super(ctx){

    }
    constructor(ctx:Context,attr:Attribute):super(ctx,attr){

    }
}

//interface User{
////    val email:String
//    val nickname:String
////        get() =  email.substringBefore("@")
//}
//
//class PrivateUser(override val nickname:String):User
//class SubscribedUser(val email:String):User{
//    override val nickname:String
//        get() = email.substringBefore("@") // 都度計算
//}
//class FacebookUser(val accountId:Int):User{
//    override val nickname = getNickname(accountId)  // 初回のみ
//
//    private fun getNickname(accountId: Int): String = "hogehoge$accountId" //
//}

class User(val name:String){
    var address = "Japan"
        set(value:String){
            println("""
                Address will changed
                "$field" -> "$value"
            """.trimIndent())
            field =value
        }
}

//fun main(args: Array<String>) {
//    val u = User("taktamur")
//    u.address = "Nagoya"
//}
data class Client2(val name:String,val postalCode:Int)

class Client(val name:String,val postalCode:Int){
    override fun toString(): String = "name=$name, postalCode=$postalCode"
    override fun hashCode(): Int = name.hashCode()*31+postalCode
    override fun equals(other: Any?): Boolean {
        return when{
            other == null -> false
            other !is Client -> false
            other.name != this.name ->false
            other.postalCode !=this.postalCode -> false
            else ->true
        }
//        if( other==null || other !is Client){
//            return false
//        }
//        return other.name ==name &&
//                other.postalCode==postalCode
    }
}

fun main(args: Array<String>) {
    val c1 = Client2("taktamur",2110025)
    val c2 = Client2("taktamur",2110025)
    val c3 = Client2("taktamur",211005)
    println(c1==c2)
    println(c1==c3)
}

class DelegateCollection<T>(
    val innerList:Collection<T> = ArrayList<T>()
):Collection<T> by innerList
