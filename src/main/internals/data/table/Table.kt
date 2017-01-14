package main.internals.data.table

import main.internals.data.misc.Predicate
import main.internals.data.misc.Predicate.Companion.TRUE
import main.internals.data.schema.Column

interface Table {
    fun addColumn(column: Column)

    fun getColumns(filter: Predicate<Column> = TRUE): List<Column>

    fun insert(column: Column)

    fun delete(predicate: Predicate<Column>)

    fun size(): Int
}