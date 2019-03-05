package com.algolia.search.model.search

import com.algolia.search.model.Raw
import com.algolia.search.serialize.KeyMin
import com.algolia.search.serialize.KeyStrict
import com.algolia.search.serialize.asJsonInput
import kotlinx.serialization.*
import kotlinx.serialization.internal.BooleanSerializer
import kotlinx.serialization.internal.StringSerializer
import kotlinx.serialization.json.boolean
import kotlinx.serialization.json.booleanOrNull
import kotlinx.serialization.json.content


@Serializable(TypoTolerance.Companion::class)
public sealed class TypoTolerance(override val raw: String) : Raw<String> {

    public data class Boolean(val boolean: kotlin.Boolean) : TypoTolerance(boolean.toString())

    public object Min : TypoTolerance(KeyMin)

    public object Strict : TypoTolerance(KeyStrict)

    public data class Other(override val raw: String) : TypoTolerance(raw)

    override fun toString(): String {
        return raw
    }

    @Serializer(TypoTolerance::class)
    internal companion object : KSerializer<TypoTolerance> {

        override fun serialize(encoder: Encoder, obj: TypoTolerance) {
            when (obj) {
                is Boolean -> BooleanSerializer.serialize(encoder, obj.boolean)
                else -> StringSerializer.serialize(encoder, obj.raw)
            }
        }

        override fun deserialize(decoder: Decoder): TypoTolerance {
            val element = decoder.asJsonInput()

            return when {
                element.booleanOrNull != null -> Boolean(element.boolean)
                else -> {
                    when (val content = element.content) {
                        KeyMin -> Min
                        KeyStrict -> Strict
                        else -> Other(content)
                    }
                }
            }
        }
    }
}