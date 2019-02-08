package com.algolia.search.model.response.deletion

import com.algolia.search.model.Datable
import com.algolia.search.serialize.KeyDeletedAt
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class Deletion(@SerialName(KeyDeletedAt) override val date: String) : Datable