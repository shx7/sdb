package main.internals.data

data class Identifier(val id: Int) {
    companion object {
        var currentId = 0

        fun nextIdentifier(): Identifier = Identifier(currentId++)
    }
}

data class RowSpec(val name: String, val type: Type)

abstract class Schema(val id: Int, val dataSpec: List<RowSpec>) {
    abstract fun addRow(row: RowSpec)
}

class IncorrectTypesException(message: String) : RuntimeException(message)

class TableCreationException(message: String) : RuntimeException(message)

