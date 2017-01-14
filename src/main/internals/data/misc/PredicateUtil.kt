package main.internals.data.misc

class Predicate<in T>(val predicate: (T) -> Boolean) {
    companion object {
        val TRUE = Predicate({ x: Any? -> true })
        val FALSE = Predicate({ x: Any? -> false })
    }

    operator fun invoke(value: T) = predicate(value)
}