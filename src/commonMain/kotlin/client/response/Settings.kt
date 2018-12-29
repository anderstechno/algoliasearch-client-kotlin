package client.response

import client.data.*
import client.serialize.*
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.*


@Serializable
data class Settings(
    var searchableAttributes: List<Attribute>? = null,
    var attributesForFaceting: List<Attribute>? = null,
    var unretrievableAttributes: List<Attribute>? = null,
    var attributesToRetrieve: List<Attribute>? = null,
    var ranking: List<Ranking>? = null,
    var customRanking: List<CustomRanking>? = null,
    var replicas: List<Index>? = null,
    var maxValuesPerFacet: Int? = null,
    var sortFacetValuesBy: SortFacetValuesBy? = null,
    var attributesToHighlight: List<Attribute>? = null,
    var attributesToSnippet: List<Snippet>? = null,
    var highlightPreTag: String? = null,
    var highlightPostTag: String? = null,
    var snippetEllipsisText: String? = null,
    var restrictHighlightAndSnippetArrays: Boolean? = null,
    var hitsPerPage: Int? = null,
    var paginationLimitedTo: Int? = null,
    var minWordSizefor1Typo: Int? = null,
    var minWordSizefor2Typos: Int? = null,
    var typoTolerance: TypoTolerance? = null,
    var allowTyposOnNumericTokens: Boolean? = null,
    var disableTypoToleranceOnAttributes: List<Attribute>? = null,
    var disableTypoToleranceOnWords: List<String>? = null,
    var separatorsToIndex: String? = null,
    var ignorePlurals: BooleanOrQueryLanguages? = null,
    var removeStopWords: BooleanOrQueryLanguages? = null,
    var camelCaseAttributes: List<Attribute>? = null,
    var decompoundedAttributes: List<DecompoundedAttributes>? = null,
    var keepDiacriticsOnCharacters: String? = null,
    var queryLanguages: List<QueryLanguage>? = null,
    var enableRules: Boolean? = null,
    var queryType: QueryType? = null,
    var removeWordsIfNoResults: RemoveWordIfNoResults? = null,
    var advancedSyntax: Boolean? = null,
    var optionalWords: List<String>? = null,
    var disablePrefixOnAttributes: List<Attribute>? = null,
    var disableExactOnAttributes: List<Attribute>? = null,
    var exactOnSingleWordQuery: ExactOnSingleWordQuery? = null,
    var alternativesAsExact: List<AlternativesAsExact>? = null,
    var numericAttributesForFiltering: List<NumericAttributeFilter>? = null,
    var allowCompressionOfIntegerArray: Boolean? = null,
    var attributeForDistinct: Attribute? = null,
    var distinct: Int? = null,
    var replaceSynonymsInHighlight: Boolean? = null,
    var minProximity: Int? = null,
    var responseFields: List<ResponseFields>? = null,
    var maxFacetHits: Int? = null
) {

    internal companion object : Serializer<Settings>, Deserializer<Settings> {

        override fun serialize(input: Settings): JsonElement {
            return input.run {
                json {
                    // Attributes
                    searchableAttributes?.let { KeySearchableAttributes to Attribute.serializeList(it) }
                    attributesForFaceting?.let { KeyAttributesForFaceting to Attribute.serializeList(it) }
                    unretrievableAttributes?.let { KeyUnretrievableAttributes to Attribute.serializeList(it) }
                    attributesToRetrieve?.let { KeyAttributesToRetrieve to Attribute.serializeList(it) }
                    // Ranking
                    ranking?.let { KeyRanking to Ranking.serializeList(it) }
                    customRanking?.let { KeyCustomRanking to CustomRanking.serializeList(it) }
                    replicas?.let { KeyReplicas to Index.serializeList(it) }
                    // Faceting
                    maxValuesPerFacet?.let { KeyMaxValuesPerFacet to it }
                    sortFacetValuesBy?.let { KeySortFacetValuesBy to SortFacetValuesBy.serialize(it) }
                    // Highlighting
                    attributesToHighlight?.let { KeyAttributesToHighlight to Attribute.serializeList(it) }
                    attributesToSnippet?.let { KeyAttributesToSnippet to Snippet.serializeList(it) }
                    highlightPreTag?.let { KeyHighlightPreTag to it }
                    highlightPostTag?.let { KeyHighlightPostTag to it }
                    snippetEllipsisText?.let { KeySnippetEllipsisText to it }
                    restrictHighlightAndSnippetArrays?.let { KeyRestrictHighlightAndSnippetArrays to it }
                    // Pagination
                    hitsPerPage?.let { KeyHitsPerPage to it }
                    paginationLimitedTo?.let { KeyPaginationLimitedTo to it }
                    // Typos
                    minWordSizefor1Typo?.let { KeyMinWordSizefor1Typo to it }
                    minWordSizefor2Typos?.let { KeyMinWordSizefor2Typos to it }
                    typoTolerance?.let { KeyTypoTolerance to TypoTolerance.serialize(it) }
                    allowTyposOnNumericTokens?.let { KeyAllowTyposOnNumericTokens to it }
                    disableTypoToleranceOnAttributes?.let {
                        KeyDisableTypoToleranceOnAttributes to Attribute.serializeList(it)
                    }
                    disableTypoToleranceOnWords?.let { KeyDisableTypoToleranceOnWords to ListSerializer.serialize(it) }
                    separatorsToIndex?.let { KeySeparatorsToIndex to it }
                    // Languages
                    ignorePlurals?.let { KeyIgnorePlurals to BooleanOrQueryLanguages.serialize(it) }
                    removeStopWords?.let { KeyRemoveStopWords to BooleanOrQueryLanguages.serialize(it) }
                    camelCaseAttributes?.let { KeyCamelCaseAttributes to Attribute.serializeList(it) }
                    decompoundedAttributes?.let { KeyDecompoundedAttributes to DecompoundedAttributes.serializeList(it) }
                    keepDiacriticsOnCharacters?.let { KeyKeepDiacriticsOnCharacters to it }
                    queryLanguages?.let { KeyQueryLanguages to QueryLanguage.serializeList(it) }
                    // Query-rules
                    enableRules?.let { KeyEnableRules to it }
                    // Query-strategy
                    queryType?.let { KeyQueryType to QueryType.serialize(it) }
                    removeWordsIfNoResults?.let { KeyRemoveWordsIfNoResults to RemoveWordIfNoResults.serialize(it) }
                    advancedSyntax?.let { KeyAdvancedSyntax to it }
                    optionalWords?.let { KeyOptionalWords to ListSerializer.serialize(it) }
                    disablePrefixOnAttributes?.let { KeyDisablePrefixOnAttributes to Attribute.serializeList(it) }
                    disableExactOnAttributes?.let { KeyDisableExactOnAttributes to Attribute.serializeList(it) }
                    exactOnSingleWordQuery?.let { KeyExactOnSingleWordQuery to ExactOnSingleWordQuery.serialize(it) }
                    alternativesAsExact?.let { KeyAlternativesAsExact to AlternativesAsExact.serializeList(it) }
                    // Performance
                    numericAttributesForFiltering?.let {
                        KeyNumericAttributesForFiltering to NumericAttributeFilter.serializeList(it)
                    }
                    allowCompressionOfIntegerArray?.let { KeyAllowCompressionOfIntegerArray to it }
                    // Advanced
                    attributeForDistinct?.let { KeyAttributeForDistinct to Attribute.serialize(it) }
                    distinct?.let { KeyDistinct to it }
                    replaceSynonymsInHighlight?.let { KeyReplaceSynonymsInHighlight to it }
                    minProximity?.let { KeyMinProximity to it }
                    responseFields?.let { KeyResponseFields to ResponseFields.serializeList(it) }
                    maxFacetHits?.let { KeyMaxFacetHits to it }
                }
            }
        }

        override fun deserialize(element: JsonElement): Settings? {
            return when (element) {
                is JsonObject -> {
                    Settings().apply {
                        element.forEach { (key, element) ->
                            when (key) {
                                // Attributes
                                KeySearchableAttributes -> searchableAttributes = Attribute.deserializeList(element)
                                KeyAttributesForFaceting -> attributesForFaceting = Attribute.deserializeList(element)
                                KeyUnretrievableAttributes -> unretrievableAttributes =
                                    Attribute.deserializeList(element)
                                KeyAttributesToRetrieve -> attributesToRetrieve = Attribute.deserializeList(element)
                                // Ranking
                                KeyRanking -> ranking = Ranking.deserializeList(element)
                                KeyCustomRanking -> customRanking = CustomRanking.deserializeList(element)
                                KeyReplicas -> replicas = Index.deserializeList(element)
                                // Faceting
                                KeyMaxValuesPerFacet -> maxValuesPerFacet = element.intOrNull
                                KeySortFacetValuesBy -> sortFacetValuesBy = SortFacetValuesBy.deserialize(element)
                                // Highlighting
                                KeyAttributesToHighlight -> attributesToHighlight = Attribute.deserializeList(element)
                                KeyAttributesToSnippet -> attributesToSnippet = Snippet.deserializeList(element)
                                KeyHighlightPreTag -> highlightPreTag = element.contentOrNull
                                KeyHighlightPostTag -> highlightPostTag = element.contentOrNull
                                KeySnippetEllipsisText -> snippetEllipsisText = element.contentOrNull
                                KeyRestrictHighlightAndSnippetArrays -> restrictHighlightAndSnippetArrays =
                                    element.booleanOrNull
                                // Pagination
                                KeyHitsPerPage -> hitsPerPage = element.intOrNull
                                KeyPaginationLimitedTo -> paginationLimitedTo = element.intOrNull
                                // Typos
                                KeyMinWordSizefor1Typo -> minWordSizefor1Typo = element.intOrNull
                                KeyMinWordSizefor2Typos -> minWordSizefor2Typos = element.intOrNull
                                KeyTypoTolerance -> typoTolerance = TypoTolerance.deserialize(element)
                                KeyAllowTyposOnNumericTokens -> allowTyposOnNumericTokens = element.booleanOrNull
                                KeyDisableTypoToleranceOnAttributes -> disableTypoToleranceOnAttributes =
                                    Attribute.deserializeList(element)
                                KeyDisableTypoToleranceOnWords -> disableTypoToleranceOnWords =
                                    ListSerializer.deserialize(element)
                                KeySeparatorsToIndex -> separatorsToIndex = element.contentOrNull
                                // Languages
                                KeyIgnorePlurals -> ignorePlurals = BooleanOrQueryLanguages.deserialize(element)
                                KeyRemoveStopWords -> removeStopWords = BooleanOrQueryLanguages.deserialize(element)
                                KeyCamelCaseAttributes -> camelCaseAttributes = Attribute.deserializeList(element)
                                KeyDecompoundedAttributes -> decompoundedAttributes =
                                    DecompoundedAttributes.deserializeList(element)
                                KeyKeepDiacriticsOnCharacters -> keepDiacriticsOnCharacters = element.contentOrNull
                                KeyQueryLanguages -> queryLanguages = QueryLanguage.deserializeList(element)
                                // Query-rules
                                KeyEnableRules -> enableRules = element.booleanOrNull
                                // Query-strategy
                                KeyQueryType -> queryType = QueryType.deserialize(element)
                                KeyRemoveWordsIfNoResults -> removeWordsIfNoResults =
                                    RemoveWordIfNoResults.deserialize(element)
                                KeyAdvancedSyntax -> advancedSyntax = element.booleanOrNull
                                KeyOptionalWords -> optionalWords = ListSerializer.deserialize(element)
                                KeyDisablePrefixOnAttributes -> disablePrefixOnAttributes =
                                    Attribute.deserializeList(element)
                                KeyDisableExactOnAttributes -> disableExactOnAttributes =
                                    Attribute.deserializeList(element)
                                KeyExactOnSingleWordQuery -> exactOnSingleWordQuery =
                                    ExactOnSingleWordQuery.deserialize(element)
                                KeyAlternativesAsExact -> alternativesAsExact =
                                    AlternativesAsExact.deserializeList(element)
                                // Performance
                                KeyNumericAttributesForFiltering -> numericAttributesForFiltering =
                                    NumericAttributeFilter.deserializeList(element)
                                KeyAllowCompressionOfIntegerArray -> allowCompressionOfIntegerArray =
                                    element.booleanOrNull
                                // Advanced
                                KeyAttributeForDistinct -> attributeForDistinct = Attribute.deserialize(element)
                                KeyDistinct -> distinct = element.intOrNull
                                KeyReplaceSynonymsInHighlight -> replaceSynonymsInHighlight = element.booleanOrNull
                                KeyMinProximity -> minProximity = element.intOrNull
                                KeyResponseFields -> responseFields = ResponseFields.deserializeList(element)
                                KeyMaxFacetHits -> maxFacetHits = element.intOrNull
                            }
                        }
                    }
                }
                else -> null
            }
        }
    }
}