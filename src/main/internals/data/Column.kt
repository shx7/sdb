package main.internals.data

abstract class Column(val data: List<Value<*>>) {
    abstract fun updateRow(rowId: Int, newValue: Value<*>)
}