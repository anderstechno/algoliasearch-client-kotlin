package com.algolia.search.saas.data

import com.algolia.search.saas.serialize.KeyObjectIds
import com.algolia.search.saas.serialize.KeyTaskId
import com.algolia.search.saas.serialize.asJsonInput
import com.algolia.search.saas.serialize.asJsonOutput
import com.algolia.search.saas.toIndexName
import com.algolia.search.saas.toObjectID
import com.algolia.search.saas.toTaskID
import kotlinx.serialization.*
import kotlinx.serialization.json.contentOrNull
import kotlinx.serialization.json.json
import kotlinx.serialization.json.jsonArray
import kotlinx.serialization.json.long


@Serializable(TaskBatchWriteIndex.Companion::class)
data class TaskBatchWriteIndex(
    @SerialName(KeyTaskId) val taskIDs: List<TaskIndex>,
    @Optional val objectIDs: List<ObjectID?>? = null
) {

    @Serializer(TaskBatchWriteIndex::class)
    internal companion object : KSerializer<TaskBatchWriteIndex> {

        override fun serialize(encoder: Encoder, obj: TaskBatchWriteIndex) {
            val json = json {
                KeyTaskId to json { obj.taskIDs.forEach { it.indexName.raw to it.taskID.raw } }
                KeyObjectIds to obj.objectIDs?.let { jsonArray { it.forEach { +it?.raw } } }
            }

            encoder.asJsonOutput().encodeJson(json)
        }

        override fun deserialize(decoder: Decoder): TaskBatchWriteIndex {
            val element = decoder.asJsonInput().jsonObject
            val taskIDs = element.getObject(KeyTaskId).map { (key, entry) ->
                TaskIndex(key.toIndexName(), entry.long.toTaskID())
            }
            val objectIDs = element.getArrayOrNull(KeyObjectIds)?.map { it.contentOrNull?.toObjectID() }

            return TaskBatchWriteIndex(taskIDs, objectIDs)
        }
    }
}