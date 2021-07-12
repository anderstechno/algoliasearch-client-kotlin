package com.algolia.search.internal

import com.algolia.search.configuration.Region
import com.algolia.search.configuration.RetryableHost

internal val Region.Personalization.hosts get() = listOf(RetryableHost("personalization.$this.algolia.com"))