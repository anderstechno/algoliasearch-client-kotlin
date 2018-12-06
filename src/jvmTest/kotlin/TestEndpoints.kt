
import client.*
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4


@RunWith(JUnit4::class)
class TestEndpoints {

    private val apiKey = ApiKey("dc8e9efcfe38f7fbfb996047af06d8c5")
    private val applicationId = ApplicationId("latency")
    private val client = Client(applicationId, apiKey)
    private val index = Index("products_android_demo")

//    @Test
//    fun listIndexes() {
//        runBlocking {
//            println(client.getListIndexes())
//        }
//    }
//
//    @Test
//    fun search() {
//        runBlocking {
//            try {
//                val response = client.search(index)
//
//                println(response)
//            } catch (exception: BadResponseStatusException) {
//                fail(exception.localizedMessage)
//            }
//        }
//    }
//
//    @Test
//    fun browse() {
//        runBlocking {
//            try {
//                val responseA = client.browse(index)
//
//                println(responseA)
//                responseA.cursor?.let {
//                    val responseB = client.browse(index, it)
//
//                    println(responseB)
//                }
//            } catch (exception: BadResponseStatusException) {
//                fail(exception.localizedMessage)
//            }
//        }
//    }
//
//    @Test
//    fun searchForFacetValue() {
//        runBlocking {
//            try {
//                val response = client.searchForFacetValue(
//                    index,
//                    "color",
//                    maxFacetHits = 2,
//                    facetQuery = "co",
//                    query = Query(maxFacetHits = 2)
//                )
//
//                println(response)
//            } catch (exception: BadResponseStatusException) {
//                exception.printStackTrace()
//                fail(exception.localizedMessage)
//            }
//        }
//    }

    @Test
    fun timeout() {
        val client = AbstractClient(applicationId, apiKey)

        runBlocking {
            client.getListIndexes()
        }
    }
}