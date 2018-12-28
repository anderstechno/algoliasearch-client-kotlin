package client.data

import client.serialize.Deserializer
import client.serialize.Serializer
import client.toAttribute
import kotlinx.serialization.json.*


data class DecompoundedAttributes internal constructor(val language: QueryLanguage, val attributes: List<Attribute>) {

    constructor(language: QueryLanguage.Finnish, vararg attributes: Attribute) : this(language, attributes.toList())
    constructor(language: QueryLanguage.German, vararg attributes: Attribute) : this(language, attributes.toList())
    constructor(language: QueryLanguage.Dutch, vararg attributes: Attribute) : this(language, attributes.toList())

    internal companion object : Serializer<DecompoundedAttributes>, Deserializer<DecompoundedAttributes> {

        override fun serialize(input: DecompoundedAttributes): JsonElement {
            return json {
                input.language.raw to jsonArray {
                    input.attributes.forEach { +it.raw }
                }
            }
        }

        override fun deserialize(element: JsonElement): DecompoundedAttributes? {
            return when (element) {
                is JsonObject -> {
                    val key = element.keys.first()
                    val attributes = element.getArrayOrNull(key)?.map { it.content.toAttribute() }

                    DecompoundedAttributes(
                        QueryLanguage.deserialize(JsonPrimitive(key))!!,
                        attributes ?: listOf()
                    )
                }
                else -> null
            }
        }
    }
}