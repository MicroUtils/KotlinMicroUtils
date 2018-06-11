package kmu

import java.io.InputStream
import java.io.OutputStream

@Suppress("ReturnCount")
fun InputStream.copyTo(
	target: OutputStream,
	bufferSize: Int = DEFAULT_BUFFER_SIZE,
	listenerStep: Long = 0L,
	listener: ((sizeCurrent: Long) -> Boolean)? = null
): Long {
	if (listenerStep < 1 || listener == null) {
		return copyTo(out = target, bufferSize = bufferSize)
	}

	var nextStep = 0L
	var bytesCopied: Long = 0
	val buffer = ByteArray(bufferSize)
	var bytes = read(buffer)
	while (bytes >= 0) {
		target.write(buffer, 0, bytes)
		bytesCopied += bytes
		bytes = read(buffer)
		if (bytesCopied > nextStep) {
			nextStep += listenerStep
			if (!listener.invoke(bytesCopied)) {
				return -1L
			}
		}
	}
	return bytesCopied
}
