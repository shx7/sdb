package main.internals.data.table

import main.internals.data.exceptions.IncorrectTypesException
import main.internals.data.misc.Predicate
import main.internals.data.schema.Column
import main.internals.data.schema.Schema
import main.internals.data.schema.Value
import java.util.*

class TableImpl(val schema: Schema) : Table {
    override fun size(): Int {
        return getColumns().size
    }

    private val columns = LinkedList<ColumnRef>()

    override fun getColumns(filter: Predicate<Column>): List<Column> {
        return columns.filter { it.valid && filter(it.data) }.map { it.data }
    }

    override fun insert(column: Column) {
        checkRestrictions(column)
        columns.add(ColumnRef(column))
    }

    override fun delete(predicate: Predicate<Column>) {
        columns.forEach { if (it.valid && predicate(it.data)) it.invalidate() }
    }

    override fun addColumn(column: Column) {
        checkTypes(schema, column.data)
        columns.add(ColumnRef(column))
    }

    private fun checkTypes(schema: Schema, data: List<Value<*>>) {
        schema.dataSpec.zip(data).forEach {
            if (it.first.type != it.second.getType()) {
                throw IncorrectTypesException("Creation of column with this schema failed")
            }
        }
    }

    private fun checkRestrictions(column: Column) {
        checkTypes(schema, column.data)
        // TODO(): Check foreign references, unique
    }

    internal class ColumnRef(val data: Column) {
        var valid: Boolean = true

        fun invalidate() {
            valid = false
        }
    }
}