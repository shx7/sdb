package main.internals.data.schema

abstract class Value<out T>(val value: T?) {
    companion object {
        fun of(value: Int?) = object : Value<Int>(value) {
            override fun getType(): Type {
                return Type.int32
            }
        }

//        TODO: figure out bug with resolve ambiguity for case Value.of(1)
//        fun ofLong(value: Long?) = object : Value<Long>(value) {
//            override fun getType(): Type {
//                return Type.int64
//            }
//        }

        fun of(value: String?) = object : Value<String>(value) {
            override fun getType(): Type {
                return Type.string
            }
        }

        fun of(value: Boolean?) = object : Value<Boolean>(value) {
            override fun getType(): Type {
                return Type.boolean
            }
        }

        fun of(value: Double?) = object : Value<Double>(value) {
            override fun getType(): Type {
                return Type.double
            }
        }
    }

    abstract fun getType(): Type
}