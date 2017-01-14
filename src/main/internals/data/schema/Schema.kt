package main.internals.data.schema

data class RowSpec(val name: String, val type: Type)

abstract class Schema(val id: Int, val dataSpec: List<RowSpec>)

class SchemaProvider {
    companion object {
        private var currentId: Int = 0

        private fun getNextId() = currentId++

        // TODO: Here we can provide with spring
        fun create() = SchemaImplBuilder(getNextId())
    }

    class SchemaImplBuilder(val id: Int) {
        private val dataSpec = mutableListOf<RowSpec>()

        fun addRowSpec(rowSpec: RowSpec): SchemaImplBuilder {
            dataSpec.add(rowSpec)
            return this
        }

        fun build() = SchemaImpl(id, dataSpec)
    }
}

class SchemaImpl(id: Int, dataSpec: List<RowSpec>) : Schema(id, dataSpec)

