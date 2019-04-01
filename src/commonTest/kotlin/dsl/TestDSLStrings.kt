package dsl

import com.algolia.search.dsl.DSLStrings
import shouldEqual
import unknown
import kotlin.test.Test


internal class TestDSLStrings {

    @Test
    fun default() {
        val dsl = DSLStrings().apply {
            +unknown
        }

        dsl.build() shouldEqual listOf(unknown)
    }
}