package dsl.filtering

import attributeA
import com.algolia.search.dsl.filtering.DSLGroupNumeric
import com.algolia.search.dsl.filtering.Filter
import com.algolia.search.dsl.filtering.NumericOperator
import shouldEqual
import kotlin.test.Test


internal class TestDSLGroupNumeric {

    @Test
    fun rangeString() {
        val dsl = DSLGroupNumeric {
            +range(attributeA.raw, 0 until 2)
            +range(attributeA.raw, 0L until 2L)
            +range(attributeA.raw, 0f, 1f)
            +range(attributeA.raw, 0.0, 1.0)
        }

        dsl shouldEqual setOf(
            Filter.Numeric(attributeA, 0 until 2),
            Filter.Numeric(attributeA, 0L until 2L),
            Filter.Numeric(attributeA, 0f, 1f),
            Filter.Numeric(attributeA, 0.0, 1.0)
        )
    }

    @Test
    fun rangeAttribute() {
        val dsl = DSLGroupNumeric {
            +range(attributeA, 0 until 2)
            +range(attributeA, 0L until 2L)
            +range(attributeA, 0f, 1f)
            +range(attributeA, 0.0, 1.0)
        }

        dsl shouldEqual setOf(
            Filter.Numeric(attributeA, 0 until 2),
            Filter.Numeric(attributeA, 0L until 2L),
            Filter.Numeric(attributeA, 0f, 1f),
            Filter.Numeric(attributeA, 0.0, 1.0)
        )
    }

    @Test
    fun comparison() {
        val dsl = DSLGroupNumeric {
            +comparison(attributeA.raw, Lesser, 0)
            +comparison(attributeA, Greater, 0)
        }

        dsl shouldEqual setOf(
            Filter.Numeric(attributeA, NumericOperator.Lesser, 0),
            Filter.Numeric(attributeA, NumericOperator.Greater, 0)
        )
    }

    @Test
    fun operator() {
        DSLGroupNumeric {
            Lesser shouldEqual NumericOperator.Lesser
            LesserOrEquals shouldEqual NumericOperator.LesserOrEquals
            Equals shouldEqual NumericOperator.Equals
            NotEquals shouldEqual NumericOperator.NotEquals
            Greater shouldEqual NumericOperator.Greater
            GreaterOrEquals shouldEqual NumericOperator.GreaterOrEquals
        }
    }
}