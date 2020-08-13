package com.algolia.search.client.internal

import com.algolia.search.client.ClientSearch
import com.algolia.search.client.Index
import com.algolia.search.configuration.CallType
import com.algolia.search.configuration.Configuration
import com.algolia.search.configuration.ConfigurationSearch
import com.algolia.search.configuration.Credentials
import com.algolia.search.dsl.requestOptionsBuilder
import com.algolia.search.endpoint.EndpointAPIKey
import com.algolia.search.endpoint.EndpointAdvanced
import com.algolia.search.endpoint.EndpointMultiCluster
import com.algolia.search.endpoint.EndpointMultipleIndex
import com.algolia.search.endpoint.internal.EndpointAPIKey
import com.algolia.search.endpoint.internal.EndpointMulticluster
import com.algolia.search.endpoint.internal.EndpointMultipleIndex
import com.algolia.search.model.IndexName
import com.algolia.search.model.LogType
import com.algolia.search.model.response.ResponseAPIKey
import com.algolia.search.model.response.ResponseBatches
import com.algolia.search.model.response.ResponseLogs
import com.algolia.search.model.response.creation.CreationAPIKey
import com.algolia.search.model.response.deletion.DeletionAPIKey
import com.algolia.search.model.task.TaskIndex
import com.algolia.search.model.task.TaskStatus
import com.algolia.search.serialize.KeyLength
import com.algolia.search.serialize.KeyOffset
import com.algolia.search.serialize.KeyType
import com.algolia.search.serialize.RouteLogs
import com.algolia.search.transport.RequestOptions
import com.algolia.search.transport.internal.Transport
import io.ktor.client.features.ResponseException
import io.ktor.http.HttpMethod
import io.ktor.http.HttpStatusCode
import kotlinx.coroutines.TimeoutCancellationException
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.withTimeout

internal class ClientSearchImpl internal constructor(
    internal val transport: Transport,
) : ClientSearch,
    EndpointMultipleIndex by EndpointMultipleIndex(transport),
    EndpointAPIKey by EndpointAPIKey(transport),
    EndpointMultiCluster by EndpointMulticluster(transport),
    Configuration by transport,
    Credentials by transport.credentials {

    /**
     *  Initialize an [Index] configured with [ConfigurationSearch].
     */
    public override fun initIndex(indexName: IndexName): Index {
        return Index(transport, indexName)
    }

    /**
     * Wait on multiple [TaskIndex] operations. To be used with [multipleBatchObjects].
     *
     * @param timeout If a value is specified, the method will throw [TimeoutCancellationException] after waiting for
     * the allotted time in milliseconds.
     */
    public override suspend fun List<TaskIndex>.waitAll(timeout: Long?): List<TaskStatus> {

        suspend fun loop(): List<TaskStatus> {
            while (true) {
                coroutineScope {
                    map { async { initIndex(it.indexName).getTask(it.taskID) } }.map { it.await().status }
                }.let {
                    if (it.all { status -> status == TaskStatus.Published }) return it
                }
                delay(1000L)
            }
        }

        return timeout?.let { withTimeout(it) { loop() } } ?: loop()
    }

    /**
     * Convenience method similar to [waitAll] but with a [ResponseBatches] as receiver.
     */
    public override suspend fun ResponseBatches.waitAll(timeout: Long?): List<TaskStatus> {
        return tasks.waitAll(timeout)
    }

    /**
     * Wait on a [CreationAPIKey] operation.
     *
     * @param timeout If a value is specified, the method will throw [TimeoutCancellationException] after waiting for
     * the allotted time in milliseconds.
     */
    public override suspend fun CreationAPIKey.wait(timeout: Long?): ResponseAPIKey {

        suspend fun loop(): ResponseAPIKey {
            while (true) {
                try {
                    return getAPIKey(apiKey)
                } catch (exception: ResponseException) {
                    if (exception.response.status.value != HttpStatusCode.NotFound.value) throw exception
                }
                delay(1000L)
            }
        }

        return timeout?.let { withTimeout(it) { loop() } } ?: loop()
    }

    /**
     * Wait on a [DeletionAPIKey] operation.
     *
     * @param timeout If a value is specified, the method will throw [TimeoutCancellationException] after waiting for
     * the allotted time in milliseconds.
     */
    public override suspend fun DeletionAPIKey.wait(timeout: Long?): Boolean {

        suspend fun loop(): Boolean {
            while (true) {
                try {
                    getAPIKey(apiKey)
                } catch (exception: ResponseException) {
                    if (exception.response.status.value == HttpStatusCode.NotFound.value) return true else throw exception
                }
                delay(1000L)
            }
        }

        return timeout?.let { withTimeout(it) { loop() } } ?: loop()
    }

    /**
     * Convenience methods to get the logs directly from the [ClientSearch] without instantiating an [Index].
     *
     * @see EndpointAdvanced.getLogs
     */
    public override suspend fun getLogs(
        page: Int?,
        hitsPerPage: Int?,
        logType: LogType?,
        requestOptions: RequestOptions?,
    ): ResponseLogs {
        val options = requestOptionsBuilder(requestOptions) {
            parameter(KeyOffset, page)
            parameter(KeyLength, hitsPerPage)
            parameter(KeyType, logType?.raw)
        }

        return transport.request(HttpMethod.Get, CallType.Read, RouteLogs, options)
    }
}
