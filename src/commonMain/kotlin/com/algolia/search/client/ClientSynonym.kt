package com.algolia.search.client

import com.algolia.search.model.*
import com.algolia.search.endpoint.EndpointSynonym
import com.algolia.search.model.common.TaskDelete
import com.algolia.search.model.common.TaskUpdate
import com.algolia.search.model.synonym.Synonym
import com.algolia.search.model.synonym.SynonymHits
import com.algolia.search.model.synonym.SynonymType
import com.algolia.search.model.synonym.TaskUpdateSynonym
import com.algolia.search.serialize.*
import io.ktor.client.request.*
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.json
import kotlinx.serialization.list


internal class ClientSynonym(
    val client: Client,
    override val indexName: IndexName
) : EndpointSynonym,
    Client by client {

    override suspend fun saveSynonym(
        synonym: Synonym,
        forwardToReplicas: Boolean?,
        requestOptions: RequestOptions?
    ): TaskUpdateSynonym {
        return write.retry(
            requestOptions.computedWriteTimeout,
            indexName.pathIndexes("/synonyms/${synonym.objectID}")
        ) { path ->
            httpClient.put<TaskUpdateSynonym>(path) {
                setRequestOptions(requestOptions)
                setForwardToReplicas(forwardToReplicas)
                body = Json.stringify(Synonym, synonym)
            }
        }
    }

    override suspend fun saveSynonyms(
        synonyms: List<Synonym>,
        forwardToReplicas: Boolean?,
        replaceExistingSynonyms: Boolean?,
        requestOptions: RequestOptions?
    ): TaskUpdate {
        return write.retry(requestOptions.computedWriteTimeout, indexName.pathIndexes("/synonyms/batch")) { path ->
            httpClient.post<TaskUpdate>(path) {
                setRequestOptions(requestOptions)
                setForwardToReplicas(forwardToReplicas)
                replaceExistingSynonyms?.let { parameter(KeyReplaceExistingSynonyms, it) }
                body = Json.stringify(Synonym.list, synonyms)
            }
        }
    }

    override suspend fun getSynonym(objectID: ObjectID, requestOptions: RequestOptions?): Synonym {
        return read.retry(requestOptions.computedReadTimeout, indexName.pathIndexes("/synonyms/$objectID")) { path ->
            httpClient.get<Synonym>(path) {
                setRequestOptions(requestOptions)
            }
        }
    }

    override suspend fun deleteSynonym(
        objectID: ObjectID,
        forwardToReplicas: Boolean?,
        requestOptions: RequestOptions?
    ): TaskDelete {
        return write.retry(requestOptions.computedWriteTimeout, indexName.pathIndexes("/synonyms/$objectID")) { path ->
            httpClient.delete<TaskDelete>(path) {
                setRequestOptions(requestOptions)
                setForwardToReplicas(forwardToReplicas)
            }
        }
    }

    override suspend fun searchSynonyms(
        query: String?,
        page: Int?,
        hitsPerPage: Int?,
        synonymType: List<SynonymType>?,
        requestOptions: RequestOptions?
    ): SynonymHits {
        return read.retry(requestOptions.computedReadTimeout, indexName.pathIndexes("/synonyms/search")) { path ->
            httpClient.post<SynonymHits>(path) {
                setRequestOptions(requestOptions)
                body = json {
                    query?.let { KeyQuery to it }
                    page?.let { KeyPage to it }
                    hitsPerPage?.let { KeyHitsPerPage to it }
                    synonymType?.let { KeyType to it.joinToString(",") { it.raw } }
                }.toString()
            }
        }
    }

    override suspend fun clearSynonyms(
        forwardToReplicas: Boolean?,
        requestOptions: RequestOptions?
    ): TaskUpdate {
        return write.retry(requestOptions.computedWriteTimeout, indexName.pathIndexes("/synonyms/clear")) { path ->
            httpClient.post<TaskUpdate>(path) {
                setRequestOptions(requestOptions)
                setForwardToReplicas(forwardToReplicas)
                body = ""
            }
        }
    }
}