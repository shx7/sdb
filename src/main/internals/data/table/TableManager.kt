package main.internals.data.table

import main.internals.data.schema.Schema

class TableManager {
    val tables: MutableMap<String, Table> = mutableMapOf()

    fun createTable(name: String, schema: Schema) {
        tables.put(name, TableImpl(schema))
    }

    fun dropTable(name: String) {
        tables.remove(name)
    }
}

