package com.algolia.search.model

import com.algolia.search.serialize.KeyAll
import com.algolia.search.serialize.KeyBuild
import com.algolia.search.serialize.KeyError
import com.algolia.search.serialize.KeyQuery
import kotlinx.serialization.Decoder
import kotlinx.serialization.Encoder
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.internal.StringSerializer


@Serializable(LogType.Companion::class)
public sealed class LogType(override val raw: String) : Raw<String> {

    public object All : LogType(KeyAll)

    public object Query : LogType(KeyQuery)

    public object Build : LogType(KeyBuild)

    public object Error : LogType(KeyError)

    public data class Other(override val raw: String) : LogType(raw)

    internal companion object : KSerializer<LogType> {

        private val serializer = StringSerializer

        override val descriptor = serializer.descriptor

        override fun serialize(encoder: Encoder, obj: LogType) {
            serializer.serialize(encoder, obj.raw)
        }

        override fun deserialize(decoder: Decoder): LogType {
            return when (val string = serializer.deserialize(decoder)) {
                KeyAll -> All
                KeyQuery -> Query
                KeyBuild -> Build
                KeyError -> Error
                else -> Other(string)
            }
        }
    }
}