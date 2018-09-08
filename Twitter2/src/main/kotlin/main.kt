import com.fasterxml.jackson.databind.ObjectMapper
import org.litote.kmongo.KMongo
import org.litote.kmongo.insertOne
import twitter4j.Status
import twitter4j.TwitterFactory
import twitter4j.TwitterObjectFactory
import twitter4j.Paging




data class Jedi(val name: String, val age: Int)

fun main(args: Array<String>) {
    val paging = Paging(1, 1000)
    val twitter = TwitterFactory.getSingleton()
    val statues =twitter.getHomeTimeline(paging)
    val client = KMongo.createClient() //get com.mongodb.MongoClient new instance
    val database = client.getDatabase("paming") //normal java driver usage
    val col = database.getCollection("twitter") //KMongo extension method
    println("count=${statues.count()}")
    statues.forEach{
        //println("${it.user.name} : ${it.text}")
        val mapper = ObjectMapper()
        val json = mapper.writeValueAsString(it)

//        println(json)
        col.insertOne(json)

    }
}