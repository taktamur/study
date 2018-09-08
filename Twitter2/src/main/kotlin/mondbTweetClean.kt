import com.fasterxml.jackson.databind.ObjectMapper
import org.litote.kmongo.KMongo
import org.litote.kmongo.insertOne
import java.util.*

fun main(args: Array<String>) {

   val client = KMongo.createClient() //get com.mongodb.MongoClient new instance
    val database = client.getDatabase("paming") //normal java driver usage
    val col = database.getCollection("twitter") //KMongo extension method
    val t = col.find().toList()
    val idArray = t
            .map { it["id"].toString() }
            .filter{ it != null }
    val map = idArray
            .fold(HashMap<String,Int>()){
                ret,element:String->
                HashMap(ret + (element to ( ret[element] ?: 0)+1))
            }
    println("$map")
}
