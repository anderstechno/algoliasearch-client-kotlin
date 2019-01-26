package client

import com.algolia.search.saas.client.ClientAlgolia
import com.algolia.search.saas.model.ACL
import io.ktor.client.features.BadResponseStatusException
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import shouldBeTrue
import shouldEqual


@RunWith(JUnit4::class)
internal class TestClientAPIKey {

    private val indexName = index.indexName
    private val admin = ClientAlgolia(applicationId, adminKey)
    private val description = "kotlin api key"

    @Test
    fun suite() {
        runBlocking {
            val create = admin.addAPIKey(
                indexes = listOf(indexName),
                rights = listOf(ACL.Search),
                description = description
            )

            admin.deleteAPIKey(create.apiKey)
        }
    }

    @Test
    fun list() {
        runBlocking {
            val get = admin.listAPIKeys()

            get.isNotEmpty().shouldBeTrue()
        }
    }

    @Test
    fun listIndex() {
        runBlocking {
            val get = admin.listIndexAPIKeys(indexName)

            get.isEmpty().shouldBeTrue()
        }
    }

    @Test
    fun listIndexes() {
        runBlocking {
            val get = admin.listIndexAPIKeys()

            get.isNotEmpty().shouldBeTrue()
        }
    }

    @Test
    fun getPermission() {
        runBlocking {
            val get = admin.getAPIKeyPermission(apiKey)

            get.rights.isNotEmpty().shouldBeTrue()
        }
    }

    @Test
    fun suiteIndex() {
        runBlocking {
            val create = admin.addIndexAPIKey(indexName, description = description)
            val maxWait = 10000L
            var time = 0L
            val increment = 1000L

            while (time < maxWait) {
                try {
                    val get = admin.getIndexAPIKey(indexName, create.apiKey)

                    get.apiKey shouldEqual create.apiKey
                    break
                } catch (exception: BadResponseStatusException) {
                    println(exception.localizedMessage)
                }
                delay(increment)
                time += increment
            }

            admin.updateIndexAPIKey(indexName, rights = listOf(ACL.Search))
            admin.deleteIndexAPIKey(indexName, create.apiKey)
        }
    }
}