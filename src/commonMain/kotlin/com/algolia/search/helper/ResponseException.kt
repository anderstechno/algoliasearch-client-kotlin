package com.algolia.search.helper

import io.ktor.client.features.ResponseException
import io.ktor.client.statement.readBytes
import io.ktor.utils.io.core.String

/**
 * Convenience method to convert [ResponseException.response] body bytes to a [String].
 */
public suspend fun ResponseException.readContent(): String? {
    return response?.readBytes()?.let { String(it) }
}
