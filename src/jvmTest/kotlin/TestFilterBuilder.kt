import client.query.Query
import client.query.helper.*
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import kotlin.test.assertEquals


@RunWith(JUnit4::class)
class TestFilterBuilder {

    private val attributeA = Attribute("attributeA")
    private val attributeB = Attribute("attributeB")
    private val attributeC = Attribute("attributeC")
    private val attributeD = Attribute("attributeD")
    private val groupA = Group("groupA")
    private val groupB = Group("groupB")

    @Test
    fun assign() {
        val query = Query()
        val helper = FilterBuilder()

        helper
            .and(Filter.Facet(attributeA, "valueA"))
            .assignTo(query)
        assertEquals("attributeA:valueA", query.filters)
    }

    @Test
    fun oneConjunctive() {
        val helper = FilterBuilder()
        val facet = Filter.Facet(attributeA, "valueA")

        helper.and(facet)
        assertEquals("attributeA:valueA", helper.build())
        helper.remove(facet)
        assertEquals("", helper.build())
    }

    @Test
    fun twoConjunctives() {
        val helper = FilterBuilder()
        val facet = Filter.Facet(attributeA, "valueA")
        val tag = Filter.Tag("valueB")

        helper.and(facet, tag)
        assertEquals("attributeA:valueA AND _tags:valueB", helper.build())
        helper.remove(facet)
        assertEquals("_tags:valueB", helper.build())
        helper.remove(tag)
        assertEquals("", helper.build())
    }

    @Test
    fun twoConjunctivesNegate() {
        val helper = FilterBuilder()
        val boolean = Filter.Boolean(attributeA, true)
        val range = Filter.Range(attributeB, 5.0, 6.0, true)

        helper.and(boolean, range)
        assertEquals("attributeA:true AND NOT attributeB:5.0 TO 6.0", helper.build())
        helper.remove(boolean)
        assertEquals("NOT attributeB:5.0 TO 6.0", helper.build())
        helper.remove(range)
        assertEquals("", helper.build())
    }

    @Test
    fun oneDisjunctiveGroup() {
        val helper = FilterBuilder()
        val comparisonA = Filter.Comparison(attributeA, NumericOperator.NotEquals, 10.0)
        val comparisonB = Filter.Comparison(attributeB, NumericOperator.Equals, 5.0, true)

        helper.or(comparisonA, comparisonB)
        assertEquals("attributeA != 10.0 OR NOT attributeB = 5.0", helper.build())
        helper.remove(comparisonA)
        assertEquals("NOT attributeB = 5.0", helper.build())
    }

    @Test
    fun oneConjunctiveAndDisjunctive() {
        val helper = FilterBuilder()
        val facet = Filter.Facet(attributeA, "valueA")
        val booleanA = Filter.Boolean(attributeB, false)
        val booleanB = Filter.Boolean(attributeC, true, true)

        helper
            .and(facet)
            .or(booleanA, booleanB)
        assertEquals("attributeA:valueA AND (attributeB:false OR NOT attributeC:true)", helper.build())
        helper.remove(booleanA)
        assertEquals("attributeA:valueA AND NOT attributeC:true", helper.build())
        helper
            .remove(booleanA, booleanB)
            .or(booleanA, booleanB)
        assertEquals("attributeA:valueA AND (attributeB:false OR NOT attributeC:true)", helper.build())
    }

    @Test
    fun twoDisjunctive() {
        val helper = FilterBuilder()
        val facetA = Filter.Facet(attributeA, "valueA")
        val facetB = Filter.Facet(attributeB, "valueB")
        val booleanA = Filter.Boolean(attributeC, false)
        val booleanB = Filter.Boolean(attributeD, true, true)

        helper
            .or(facetA, facetB)
            .or(booleanA, booleanB)
        assertEquals(
            "(attributeA:valueA OR attributeB:valueB) AND (attributeC:false OR NOT attributeD:true)",
            helper.build()
        )
        helper.remove(booleanA, booleanB)
        assertEquals("attributeA:valueA OR attributeB:valueB", helper.build())
    }

    @Test
    fun replace() {
        val helper = FilterBuilder()
        val facetA = Filter.Facet(attributeA, "valueA")
        val facetB = Filter.Facet(attributeB, "valueB")
        val facetC = Filter.Facet(attributeC, "valueC")

        helper
            .and(facetA, facetB)
            .or(facetA, facetB)
        assertEquals(
            "attributeA:valueA AND attributeB:valueB AND (attributeA:valueA OR attributeB:valueB)",
            helper.build()
        )
        helper.replace(facetA, facetC)
        assertEquals(
            "attributeC:valueC AND attributeB:valueB AND (attributeC:valueC OR attributeB:valueB)",
            helper.build()
        )
    }

    @Test
    fun clear() {
        val helper = FilterBuilder()
        val facetA = Filter.Facet(attributeA, "valueA")
        val facetB = Filter.Facet(attributeB, "valueB")

        helper
            .and(facetA, facetB)
            .or(facetA, facetB)
        assertEquals(
            "attributeA:valueA AND attributeB:valueB AND (attributeA:valueA OR attributeB:valueB)",
            helper.build()
        )
        helper.clear()
        assertEquals("", helper.build())
    }

    @Test
    fun group() {
        val helper = FilterBuilder()
        val filterA = Filter.Facet(attributeA, "valueA", group = groupA)
        val filterB = Filter.Boolean(attributeB, true, group = groupB)
        val filterC = Filter.Comparison(attributeC, NumericOperator.Greater, 10.0, group = groupA)

        helper.and(filterA, filterB, filterC)
        assertEquals(listOf(filterA, filterC), helper.getFilters(groupA))
        assertEquals("attributeA:valueA AND attributeB:true AND attributeC > 10.0", helper.build())
        helper.clear(groupA)
        assertEquals("attributeB:true", helper.build())
    }

    @Test
    fun replaceAttribute() {
        val helper = FilterBuilder()
        val filterA = Filter.Facet(attributeA, "valueA", group = groupA)
        val filterB = Filter.Boolean(attributeA, true, group = groupB)
        val filterC = Filter.Comparison(attributeA, NumericOperator.Greater, 10.0, group = groupA)

        helper.and(filterA, filterB, filterC)
        assertEquals("attributeA:valueA AND attributeA:true AND attributeA > 10.0", helper.build())
        helper.replaceAttribute(attributeA, attributeB)
        assertEquals("attributeB:valueA AND attributeB:true AND attributeB > 10.0", helper.build())
        helper.replaceAttribute(attributeB, attributeC, groupA)
        assertEquals("attributeC:valueA AND attributeB:true AND attributeC > 10.0", helper.build())
    }
}