package main.internals.data

abstract class Value<out T>(val value: T?) {
    abstract fun getType(): Type
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

