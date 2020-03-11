package com.algolia.search.model.personalization

import com.algolia.search.model.Attribute
import com.algolia.search.model.insights.EventName
import kotlinx.serialization.Serializable

@Deprecated(
    message = "Models are deprecated please use models located in com.algolia.search.model.recommendation.",
    level = DeprecationLevel.WARNING
)
@Serializable
public data class PersonalizationStrategy(
    /**
     * Associate a [EventScoring] to an [EventName].
     */
    val eventsScoring: Map<EventName, EventScoring>,
    /**
     * Associate a [FacetScoring] to an [Attribute].
     */
    val facetsScoring: Map<Attribute, FacetScoring>
)
