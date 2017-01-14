package main.internals.data.schema

abstract class Column(val data: List<Value<*>>) {
    abstract fun updateRow(rowId: Int, newValue: Value<*>)
}

class ColumnImpl(data: List<Value<*>>) : Column(data) {
    override fun updateRow(rowId: Int, newValue: Value<*>) = TODO()
}