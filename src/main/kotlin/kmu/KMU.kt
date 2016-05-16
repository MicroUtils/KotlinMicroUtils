
package kmu

inline fun <T> T.match(predicate: (T) -> Boolean) = predicate(this)