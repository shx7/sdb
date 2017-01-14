package main

import main.internals.data.Schema
import main.internals.data.Table
import main.internals.data.TableCreationException
import main.internals.data.TableImpl

class TableManager {
    val tables: MutableMap<String, Table> = mutableMapOf()

    fun createTable(name: String, schema: Schema) {
        assertTableExists(name, false)
        tables.put(name, TableImpl(schema))
    }

    fun dropTable(name: String) {
        assertTableExists(name, true)
        tables.remove(name)
    }

    private fun assertTableExists(name: String, expectedExistence: Boolean) {
        if (tables.containsKey(name) != expectedExistence) {
            throw TableCreationException("This table is " + if (expectedExistence) "not exists" else "already exists")
        }
    }
}

