package data

import junit.framework.TestCase
import main.internals.data.schema.RowSpec
import main.internals.data.schema.SchemaProvider
import main.internals.data.schema.Type

class SchemaProviderTest : TestCase() {
    fun testCreateSchema() {
        val rows = listOf(RowSpec("row1", Type.int32), RowSpec("row2", Type.string))
        val schema1 = SchemaProvider.create()
        val schema2 = SchemaProvider.create()
        rows.forEach {
            schema1.addRowSpec(it)
            schema2.addRowSpec(it)
        }
        assertTrue(schema1.id == 0)
        assertTrue(schema2.id == 1)
        assertEquals(rows, schema1.build().dataSpec)
        assertEquals(rows, schema2.build().dataSpec)
    }
}