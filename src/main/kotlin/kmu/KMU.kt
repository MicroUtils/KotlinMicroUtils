
package kmu

inline fun <T> T.match(predicate: (T) -> Boolean) = predicate(this)

@Suppress("FunctionName", "NOTHING_TO_INLINE")
inline fun <T> XXX(): T = throw NotImplementedError()

fun <T> T.printDebug() : T = this.apply { println(this) }
