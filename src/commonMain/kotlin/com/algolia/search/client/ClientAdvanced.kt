package com.algolia.search.client

import com.algolia.search.endpoint.EndpointAdvanced
import com.algolia.search.model.IndexName
import com.algolia.search.model.task.Task
import com.algolia.search.model.task.TaskID
import com.algolia.search.model.task.TaskInfo
import com.algolia.search.model.task.TaskStatus
import io.ktor.client.request.get
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay


internal class ClientAdvanced(
    val client: Client,
    override val indexName: IndexName
) : EndpointAdvanced,
    Client by client {

    override suspend fun List<Task>.wait(requestOptions: RequestOptions?): List<TaskStatus> {
        return coroutineScope {
            map { async { it.wait(requestOptions) } }.map { it.await() }
        }
    }

    override suspend fun Task.wait(requestOptions: RequestOptions?): TaskStatus {
        return waitTask(taskID, requestOptions)
    }

    override suspend fun waitTask(taskID: TaskID, requestOptions: RequestOptions?): TaskStatus {
        while (true) {
            getTask(taskID, requestOptions).status.let {
                if (it == TaskStatus.Published) return it
            }
            delay(2000L)
        }
    }

    override suspend fun getTask(taskID: TaskID, requestOptions: RequestOptions?): TaskInfo {
        return read.retry(requestOptions.computedReadTimeout, indexName.pathIndexes("/task/$taskID")) { path ->
            httpClient.get<TaskInfo>(path) {
                setRequestOptions(requestOptions)
            }
        }
    }
}