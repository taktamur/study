import twitter4j.*
import twitter4j.auth.AccessToken

fun main(args: Array<String>) {

    val twitter =TwitterFactory.getSingleton()
    val statues =twitter.getHomeTimeline()
    statues.forEach{
        println("${it.user.name} : ${it.text}")

    }
}