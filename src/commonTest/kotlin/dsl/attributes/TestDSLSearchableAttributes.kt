package dsl.attributes

import attributeA
import attributeB
import com.algolia.search.dsl.attributes.DSLSearchableAttributes
import com.algolia.search.model.settings.SearchableAttribute
import shouldEqual
import kotlin.test.Test


internal class TestDSLSearchableAttributes {

    @Test
    fun default() {
        val dsl = DSLSearchableAttributes {
            +"attributeA"
            +attributeB
        }

        dsl shouldEqual listOf(
            SearchableAttribute.Default(attributeA),
            SearchableAttribute.Default(attributeB)
        )
    }

    @Test
    fun multiple() {
        val dsl = DSLSearchableAttributes {
            +("attributeA" and "attributeB")
            +(attributeA and attributeB)
        }

        dsl shouldEqual listOf(
            SearchableAttribute.Default(attributeA, attributeB),
            SearchableAttribute.Default(attributeA, attributeB)
        )
    }

    @Test
    fun modifier() {
        val dsl = DSLSearchableAttributes {
            +("attributeA" modify Ordered)
            +(attributeB modify Unordered)
        }

        dsl shouldEqual listOf(
            SearchableAttribute.Ordered(attributeA),
            SearchableAttribute.Unordered(attributeB)
        )
    }
}