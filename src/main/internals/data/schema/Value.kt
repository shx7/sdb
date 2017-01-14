package main.internals.data.schema

abstract class Value<out T>(val value: T?) {
    abstract fun getType(): Type
}

class StringValue(value: String?) : Value<String>(value) {
    override fun getType(): Type {
        return Type.string
    }
}

class Int32Value(value: Int?) : Value<Int>(value) {
    override fun getType(): Type {
        return Type.int32
    }
}

class Int64Value(value: Long?) : Value<Long>(value) {
    override fun getType(): Type {
        return Type.int64
    }
}

