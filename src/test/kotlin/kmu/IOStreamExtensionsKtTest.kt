package kmu

import io.kotlintest.data.forall
import io.kotlintest.shouldBe
import io.kotlintest.specs.FunSpec
import io.kotlintest.tables.row
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream

class IOStreamExtensionsKtTest : FunSpec({
	test("copyTo") {
		forall(
			row(1, 1L, 100, 100, 100),
			row(2, 1L, 100, 50, 100),
			row(3, 1L, 100, 34, 100),
			row(1, 2L, 100, 50, 99),
			row(1, 3L, 100, 34, 100),
			row(3, 2L, 100, 34, 100),
			row(3, 4L, 100, 25, 99),
			row(1, 3L, 300, 100, 298),
			row(2, 3L, 300, 100, 298),
			row(3, 3L, 300, 100, 300),
			row(3, 4L, 300, 75, 297),
			row(3, 5L, 300, 60, 297),
			row(3, 6L, 300, 50, 297),
			row(4, 3L, 300, 75, 300),
			row(5, 3L, 300, 60, 300),
			row(6, 3L, 300, 50, 300),
			row(6, 7L, 300, 43, 300),
			row(6, 8L, 300, 38, 300)
		) { bufferSize, listenerStep, arraySize, expectedCount, expectedListenerLastReading ->
			val byteArray = ByteArray(arraySize)
			byteArray.fill(1)
			val isValue = ByteArrayInputStream(byteArray)

			val expectedOutputStream = ByteArrayOutputStream()
			var actualCount = 0
			var actualListenerLastReading: Long? = null
			isValue.copyTo(expectedOutputStream, bufferSize, listenerStep) { sizeCurrent ->
				actualListenerLastReading = sizeCurrent
				actualCount++
				return@copyTo true
			}
			val expectedByteArray = expectedOutputStream.toByteArray()
			expectedByteArray.size shouldBe arraySize
			expectedByteArray shouldBe byteArray
			actualCount shouldBe expectedCount
			actualListenerLastReading shouldBe expectedListenerLastReading
		}
	}
})
