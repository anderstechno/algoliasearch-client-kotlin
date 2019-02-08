package serialize

import attributeA
import com.algolia.search.model.SearchableAttribute
import com.algolia.search.model.SearchableAttribute.*
import com.algolia.search.serialize.KeyOrdered
import com.algolia.search.serialize.KeyUnordered
import kotlinx.serialization.json.JsonLiteral
import org.junit.runner.RunWith
import org.junit.runners.JUnit4


@RunWith(JUnit4::class)
internal class TestSearchableAttribute : TestSerializer<SearchableAttribute>(SearchableAttribute) {

    override val items = listOf(
        Ordered(attributeA) to JsonLiteral("$KeyOrdered($attributeA)"),
        Unordered(attributeA) to JsonLiteral("$KeyUnordered($attributeA)"),
        Default(attributeA) to JsonLiteral(attributeA.raw)
    )
}