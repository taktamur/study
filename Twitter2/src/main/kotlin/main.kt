import com.fasterxml.jackson.databind.ObjectMapper
import twitter4j.Status
import twitter4j.TwitterFactory
import twitter4j.TwitterObjectFactory

fun main(args: Array<String>) {

    val twitter = TwitterFactory.getSingleton()
    val statues =twitter.getHomeTimeline()
    statues.forEach{
        //println("${it.user.name} : ${it.text}")
        val mapper = ObjectMapper()
        val json = mapper.writeValueAsString(it)

        println(json)

    }
}