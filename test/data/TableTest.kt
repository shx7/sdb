package data

import junit.framework.Assert
import junit.framework.TestCase
import main.internals.data.exceptions.TableCreationException
import main.internals.data.misc.Predicate
import main.internals.data.schema.*
import main.internals.data.table.Table
import main.internals.data.table.TableImpl
import main.internals.data.table.TableManager

class TableTest : TestCase() {
    fun testTableCreation() {
        val table = createSimpleTable()
        table.addColumn(ColumnImpl(listOf(Value.of(1), Value.of("Dagny"))))
        table.addColumn(ColumnImpl(listOf(Value.of(2), Value.of("John"))))
        Assert.assertEquals(table.getColumns().size, 2)
    }

    fun testTableSimpleOperations() {
        val table = createSimpleTable()
        table.addColumn(ColumnImpl(listOf(Value.of(1), Value.of("Dagny"))))
        table.addColumn(ColumnImpl(listOf(Value.of(2), Value.of("John1"))))
        table.addColumn(ColumnImpl(listOf(Value.of(3), Value.of("John2"))))
        table.addColumn(ColumnImpl(listOf(Value.of(4), Value.of("John3"))))
        table.delete(Predicate { column -> (column.data.get(0).value as Int) == 1 })
        assertEquals(table.size(), 3)
        table.delete(Predicate { column -> (column.data.get(0).value as Int) == 2 })
        assertEquals(table.size(), 2)
        table.addColumn(ColumnImpl(listOf(Value.of(2), Value.of("Johny_dup"))))
        table.addColumn(ColumnImpl(listOf(Value.of(2), Value.of("Johny_dup"))))
        assertEquals(table.size(), 4)
        table.delete(Predicate { column -> (column.data.get(1).value as String) == "Johny_dup" })
        assertEquals(table.size(), 2)
        table.delete(Predicate.TRUE)
        assertEquals(table.size(), 0)
    }

    fun testTableManager() {
        val manager = TableManager()
        manager.createTable("foo", getSimpleSchema())
        manager.createTable("bar", getSimpleSchema())
        assertTableExists("foo", true, manager)
        assertTableExists("bar", true, manager)
        manager.dropTable("foo")
        assertTableExists("foo", false, manager)
        assertTableExists("bar", true, manager)
        manager.dropTable("bar")
        assertTableExists("foo", false, manager)
        assertTableExists("bar", false, manager)
    }

    private fun createSimpleTable(): Table {
        val schema = getSimpleSchema()
        return TableImpl(schema)
    }

    private fun getSimpleSchema() = SchemaProvider.create()
            .addRowSpec(RowSpec("id", Type.int32))
            .addRowSpec(RowSpec("name", Type.string))
            .build()

    private fun assertTableExists(name: String, expectedExistence: Boolean, manager: TableManager) {
        if (manager.tables.containsKey(name) != expectedExistence) {
            throw TableCreationException("This table is " + if (expectedExistence) "not exists" else "already exists")
        }
    }
}