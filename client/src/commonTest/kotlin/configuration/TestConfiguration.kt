package configuration

import com.algolia.search.configuration.AlgoliaSearchClient
import com.algolia.search.configuration.ConfigurationInsights
import com.algolia.search.configuration.ConfigurationPlaces
import com.algolia.search.configuration.ConfigurationSearch
import com.algolia.search.configuration.internal.DEFAULT_READ_TIMEOUT
import com.algolia.search.configuration.internal.DEFAULT_WRITE_TIMEOUT
import com.algolia.search.internal.BuildConfig
import com.algolia.search.model.APIKey
import com.algolia.search.model.ApplicationID
import com.algolia.search.transport.internal.insightHosts
import com.algolia.search.transport.internal.placesHosts
import com.algolia.search.transport.internal.searchHosts
import io.ktor.client.features.logging.LogLevel
import kotlin.test.Test
import shouldBeNull
import shouldEqual

internal class TestConfiguration {

    private val applicationID = ApplicationID("applicationID")
    private val apiKey = APIKey("apiKey")

    @Test
    fun testAlgoliaSearchClient() {
        AlgoliaSearchClient.version shouldEqual BuildConfig.version
    }

    @Test
    fun configurationSearch() {
        ConfigurationSearch(applicationID, apiKey).apply {
            writeTimeout shouldEqual DEFAULT_WRITE_TIMEOUT
            readTimeout shouldEqual DEFAULT_READ_TIMEOUT
            logLevel shouldEqual LogLevel.NONE
            hosts shouldEqual applicationID.searchHosts
            defaultHeaders.shouldBeNull()
            engine.shouldBeNull()
            httpClientConfig.shouldBeNull()
        }
    }

    @Test
    fun configurationInsights() {
        ConfigurationInsights(applicationID, apiKey).apply {
            writeTimeout shouldEqual DEFAULT_WRITE_TIMEOUT
            readTimeout shouldEqual DEFAULT_READ_TIMEOUT
            logLevel shouldEqual LogLevel.NONE
            hosts shouldEqual insightHosts
            defaultHeaders.shouldBeNull()
            engine.shouldBeNull()
            httpClientConfig.shouldBeNull()
        }
    }

    @Test
    fun configurationPlaces() {
        ConfigurationPlaces().apply {
            writeTimeout shouldEqual DEFAULT_WRITE_TIMEOUT
            readTimeout shouldEqual DEFAULT_READ_TIMEOUT
            logLevel shouldEqual LogLevel.NONE
            hosts shouldEqual placesHosts
            defaultHeaders.shouldBeNull()
            engine.shouldBeNull()
            httpClientConfig.shouldBeNull()
        }
    }
}