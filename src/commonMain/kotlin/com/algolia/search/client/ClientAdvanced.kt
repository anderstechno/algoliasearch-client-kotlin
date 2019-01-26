package com.algolia.search.client

import com.algolia.search.model.*
import com.algolia.search.endpoint.EndpointAdvanced
import com.algolia.search.model.common.Task
import com.algolia.search.model.common.TaskID
import com.algolia.search.model.common.TaskInfo
import com.algolia.search.model.common.TaskStatus
import io.ktor.client.request.get
import kotlinx.coroutines.delay


internal class ClientAdvanced(
    val client: Client,
    override val indexName: IndexName
) : EndpointAdvanced,
    Client by client {

    override suspend fun Task.wait(requestOptions: RequestOptions?): TaskStatus {
        return waitTask(taskID)
    }

    override suspend fun waitTask(taskID: TaskID, requestOptions: RequestOptions?): TaskStatus {
        while (true) {
            getTask(taskID, requestOptions).let {
                if (it == TaskStatus.Published) return it
            }
            delay(2000L)
        }
    }

    override suspend fun getTask(taskID: TaskID, requestOptions: RequestOptions?): TaskStatus {
        return read.retry(readTimeout, indexName.pathIndexes("/task/$taskID")) { path ->
            httpClient.get<TaskInfo>(path) {
                setRequestOptions(requestOptions)
            }.status
        }
    }
}