package main.internals.data

abstract class Table(val schema: Schema) {
    abstract fun addColumn(column: Column)

    abstract fun getColumns(): List<Column>
}

class TableImpl(schema: Schema) : Table(schema) {
    private val columns = mutableListOf<Column>()

    override fun getColumns(): List<Column> {
        return columns
    }

    override fun addColumn(column: Column) {
        checkTypes(schema, column.data)
        columns.add(column)
    }

    private fun checkTypes(schema: Schema, data: List<Value<*>>) {
        schema.dataSpec.zip(data).forEach {
            if (it.first.type != it.second.getType()) {
                throw IncorrectTypesException("Creation of column with this schema failed")
            }
        }
    }
}