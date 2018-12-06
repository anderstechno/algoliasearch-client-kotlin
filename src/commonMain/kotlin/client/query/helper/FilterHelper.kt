package client.query.helper

import client.query.Query

/**
 * For better understanding of Filters, please read the documentation linked below:
 *
 * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/filters/]
 */
class FilterHelper {

    /**
     *
     * To represent our SQL-like syntax of filters, we use a nested array of [Filter].
     * Each nested MutableList<Filter> represents a group. If this group contains two or more elements,
     * it will be considered  as a disjunctive group (Operator OR).
     * The operator AND will be used between each nested group.
     *
     * Example:
     *
     * ((FilterA), (FilterB), (FilterC, FilterD), (FilterE, FilterF))
     *
     * will give us the following SQL-like expression:
     *
     * FilterA AND FilterB AND (FilterC OR FilterD) AND (FilterE OR FilterF)
     */
    private val filters = mutableListOf<MutableList<Filter>>()

    /**
     * @param filter One or many [Filter].
     *
     * Add one or several conjunctive [Filter] to the [filters] list.
     * Adding several filters will result in the following expression: FilterA AND FilterB AND ...
     */
    fun addFilterAnd(vararg filter: Filter): FilterHelper {
        filter.forEach {
            filters += mutableListOf(it)
        }
        return this
    }

    /**
     * You can only create a disjunctive group of filters with exactly the same [Filter] type.
     * Public methods [addFilterOr] with the same [Filter] parameters in the method signature enforce this rule.
     */
    private fun addDisjunctiveGroupInternal(vararg filter: Filter): FilterHelper {
        filters += mutableListOf(*filter)
        return this
    }

    /**
     * @param first The first [Filter.Facet].
     * @param second the second [Filter.Facet].
     * @param filter Between 0 and N other [Filter.Facet].
     *
     * Add at least two [Filter.Facet] to the [filters] list as a disjunctive group.
     * Calling this method will result in the following expression: ... AND (FilterA OR FilterB OR ...) AND ...
     */
    fun addFilterOr(first: Filter.Facet, second: Filter.Facet, vararg filter: Filter.Facet): FilterHelper {
        return addDisjunctiveGroupInternal(first, second, *filter)
    }

    /**
     * @param first The first [Filter.Facet].
     * @param second the second [Filter.Boolean].
     * @param filter Between 0 and N other [Filter.Boolean].
     *
     * Add at least two [Filter.Boolean] to the [filters] list as a disjunctive group.
     * Calling this method will result in the following expression: ... AND (FilterA OR FilterB OR ...) AND ...
     */
    fun addFilterOr(first: Filter.Boolean, second: Filter.Boolean, vararg filter: Filter.Boolean): FilterHelper {
        return addDisjunctiveGroupInternal(first, second, *filter)
    }

    /**
     * @param first The first [Filter.Tag].
     * @param second the second [Filter.Tag].
     * @param filter Between 0 and N other [Filter.Tag].
     *
     * Add at least two [Filter.Tag] to the [filters] list as a disjunctive group.
     * Calling this method will result in the following expression: ... AND (FilterA OR FilterB OR ...) AND ...
     */
    fun addFilterOr(first: Filter.Tag, second: Filter.Range, vararg filter: Filter.Tag): FilterHelper {
        return addDisjunctiveGroupInternal(first, second, *filter)
    }

    /**
     * @param first The first [Filter.Comparison].
     * @param second the second [Filter.Comparison].
     * @param filter Between 0 and N other [Filter.Comparison].
     *
     * Add at least two [Filter.Comparison] to the [filters] list as a disjunctive group.
     * Calling this method will result in the following expression: ... AND (FilterA OR FilterB OR ...) AND ...
     */
    fun addFilterOr(
        first: Filter.Comparison,
        second: Filter.Comparison,
        vararg filter: Filter.Comparison
    ): FilterHelper {
        return addDisjunctiveGroupInternal(first, second, *filter)
    }

    /**
     * @param first The first [Filter.Range].
     * @param second the second [Filter.Range].
     * @param filter Between 0 and N other [Filter.Range].
     *
     * Add at least two [Filter.Range] to the [filters] list as a disjunctive group.
     * Calling this method will result in the following expression: ... AND (FilterA OR FilterB OR ...) AND ...
     */
    fun addFilterOr(first: Filter.Range, second: Filter.Range, vararg filter: Filter.Range): FilterHelper {
        return addDisjunctiveGroupInternal(first, second, *filter)
    }

    /**
     * You can only replace a [Filter] by another [Filter] of exactly the same type.
     * Public methods [replace] with the same [Filter] parameters in the method signature enforce this rule.
     */
    private fun replaceInternal(filter: Filter, replacement: Filter): FilterHelper {
        filters.forEach { filters ->
            val index = filters.indexOf(filter)

            if (index != -1) {
                filters.removeAt(index)
                filters.add(index, replacement)
            }
        }
        return this
    }

    /**
     * @param filter The [Filter.Facet] that will be replaced.
     * @param replacement The [Filter.Facet] used as a replacement.
     *
     * This method will search the [filters] list for the [filter] that match, and replace it with [replacement].
     */
    fun replace(filter: Filter.Facet, replacement: Filter.Facet): FilterHelper {
        return replaceInternal(filter, replacement)
    }

    /**
     * @param filter The [Filter.Boolean] that will be replaced.
     * @param replacement The [Filter.Boolean] used as a replacement.
     *
     * This method will search the [filters] list for the [filter] that match, and replace it with [replacement].
     */
    fun replace(filter: Filter.Boolean, replacement: Filter.Boolean): FilterHelper {
        return replaceInternal(filter, replacement)
    }

    /**
     * @param filter The [Filter.Tag] that will be replaced.
     * @param replacement The [Filter.Tag] used as a replacement.
     *
     * This method will search the [filters] list for the [filter] that match, and replace it with [replacement].
     */
    fun replace(filter: Filter.Tag, replacement: Filter.Tag): FilterHelper {
        return replaceInternal(filter, replacement)
    }

    /**
     * @param filter The [Filter.Comparison] that will be replaced.
     * @param replacement The [Filter.Comparison] used as a replacement.
     *
     * This method will search the [filters] list for the [filter] that match, and replace it with [replacement].
     */
    fun replace(filter: Filter.Comparison, replacement: Filter.Comparison): FilterHelper {
        return replaceInternal(filter, replacement)
    }

    /**
     * @param filter The [Filter.Range] that will be replaced.
     * @param replacement The [Filter.Range] used as a replacement.
     *
     * Search the [filters] list for the [filter] that match, and replace it with [replacement].
     */
    fun replace(filter: Filter.Range, replacement: Filter.Range): FilterHelper {
        return replaceInternal(filter, replacement)
    }

    /**
     * @param filter The [Filter] to remove.
     *
     * Remove all occurrences of [filter] inside the [filters] list.
     */
    fun remove(vararg filter: Filter): FilterHelper {
        filter.forEach {
            filters.forEach { filters -> filters.remove(it) }
        }
        filters.removeAll { it.isEmpty() }
        return this
    }

    /**
     * Remove all [Filter] from the [filters] list.
     */
    fun clear(): FilterHelper {
        filters.clear()
        return this
    }

    /**
     * @param variant The [Filter.variant] used for matching.
     *
     * Retrieve all [Filter] in the [filters] list matching the [variant].
     */
    fun getVariant(variant: String): List<Filter> {
        return filters.flatMap {
            it.filter { it.variant == variant }
        }
    }

    /**
     * @param variant The variant matching [Filter.variant].
     *
     * Remove all [Filter] in [filters] that match the [variant].
     */
    fun clear(variant: String): FilterHelper {
        filters.forEach {
            it.removeAll { it.variant == variant }
        }
        filters.removeAll { it.isEmpty() }
        return this
    }

    /**
     * @param attribute The attribute matching [Filter.attribute].
     * @param replacement Value used to replace the attribute that matched.
     * @param variant A variant used for finer grained replacement.
     *
     * Use this method to replace all [Filter] in the [filters] list which have the same [Filter.attribute]
     * as the specified [attribute] with the [replacement]. If you specify a [variant],
     * only [Filter] having a matching [Filter.variant] will have its [Filter.attribute] replaced by the [replacement].
     *
     * Example:
     *
     * ```
     * val helper = FilterHelper()
     *
     * val filterA = Filter.Facet("attributeA", "valueA", "variantA")
     * val filterB = Filter.Facet("attributeA", "valueB", "variantB")
     *
     * helper.addFilterAnd(filterA, filterB)
     * assertEquals("attributeA:valueA AND attributeA:valueB", helper.build())
     *
     * helper.replaceAttribute(attribute = "attributeA", replacement = "attributeC", variant = "variantA")
     * assertEquals("attributeC:valueA", "attributeA:valueB", helper.build())
     *
     * ```
     *
     * As you can see, only the filter with "variantA" was replaced, despite both filters having "attributeA" marked to
     * be replaced by "attributeC".
     * In this example, if no variant would have been specified (variant = null), both filters would have been affected.
     */
    fun replaceAttribute(attribute: String, replacement: String, variant: String? = null): FilterHelper {
        filters.forEach { filters ->
            val list =
                filters.filter {
                    it.attribute == attribute && if (variant != null) variant == it.variant else true
                }

            list.forEach {
                val index = filters.indexOf(it)

                filters.removeAt(index)
                filters.add(index, modifyAttribute(it, replacement))
            }
        }
        return this
    }

    private fun modifyAttribute(filter: Filter, attribute: String): Filter {
        return when (filter) {
            is Filter.Comparison -> filter.copy(attribute = attribute)
            is Filter.Tag -> filter
            is Filter.Boolean -> filter.copy(attribute = attribute)
            is Filter.Facet -> filter.copy(attribute = attribute)
            is Filter.Range -> filter.copy(attribute = attribute)
        }
    }

    /**
     * @param query One or several [Query] that will have the filters assigned to.
     *
     * Build the [filters] list into a SQL-like syntax using the [build] method,
     * and assign it to one or several [Query].
     * Each change to the [FilterHelper] should be followed by calling this method
     * for changes to be taken into account by the [Query].
     */
    fun buildAndAssign(vararg query: Query) {
        query.forEach {
            it.filters = build()
        }
    }

    /**
     * @return The SQL-like syntax represented by a [String]
     *
     * Build the [filters] list into a SQL-like syntax.
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/filters/]
     */
    fun build(): String {
        return filters.joinToString(separator = " AND ") { group ->
            val prefix = if (group.size == 1 || filters.size == 1) "" else "("
            val postfix = if (group.size == 1 || filters.size == 1) "" else ")"

            group.joinToString(prefix = prefix, postfix = postfix, separator = " OR ") {
                it.build()
            }
        }
    }
}