
package kmu

inline fun <T> T.match(predicate: (T) -> Boolean) = predicate(this)

inline fun <T> XXX(): T = throw NotImplementedError()