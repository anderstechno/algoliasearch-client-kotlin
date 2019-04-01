package dsl.filtering

import attributeA
import attributeB
import com.algolia.search.dsl.filtering.DSLFacetFilters
import com.algolia.search.dsl.filtering.Filter
import com.algolia.search.dsl.filtering.GroupAnd
import com.algolia.search.dsl.filtering.GroupOr
import shouldEqual
import kotlin.test.Test


internal class TestDSLFacetFilters {

    @Test
    fun and() {
        val dsl = DSLFacetFilters {
            and {
                +facet(attributeA, 0)
                +facet(attributeA, 1)
            }
        }

        dsl shouldEqual listOf(
            GroupAnd.Facet(Filter.Facet(attributeA, 0), Filter.Facet(attributeA, 1))
        )
    }

    @Test
    fun or() {
        val dsl = DSLFacetFilters {
            or {
                +facet(attributeA, 0)
                +facet(attributeB, 1)
            }
            or {
                +facet(attributeA, 0)
            }
        }

        dsl shouldEqual listOf(
            GroupOr.Facet(Filter.Facet(attributeA, 0), Filter.Facet(attributeB, 1)),
            GroupOr.Facet(Filter.Facet(attributeA, 0))
        )
    }

    @Test
    fun emptyGroups() {
        val dsl = DSLFacetFilters {
            and { }
            or { }
        }

        dsl shouldEqual listOf()
    }
}