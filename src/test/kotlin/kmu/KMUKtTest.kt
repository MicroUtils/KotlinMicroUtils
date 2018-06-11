package kmu

import io.kotlintest.*
import io.kotlintest.specs.FunSpec
import java.io.ByteArrayOutputStream
import java.io.PrintStream

data class TData(val id: String, val l: Long = 2L)

class KMUKtTestMatch : FunSpec({
	test("match()") {
		"tet".match { it.contains("et") } shouldBe true
	}
	test("match TData") {
		TData(id = "").match { it.l == 2L } shouldBe true
		TData(id = "").match { it.l == 1L } shouldBe false
	}
})

class KMUKtTestXXX : FunSpec({
	test("XXX()") {
		shouldThrow<NotImplementedError> {
			XXX()
		}.message shouldBe "An operation is not implemented."
		shouldThrow<NotImplementedError> {
			XXX<Long>()
		}.message shouldBe "An operation is not implemented."
		shouldThrow<NotImplementedError> {
			XXX<String>()
		}.message shouldBe "An operation is not implemented."
		shouldThrow<NotImplementedError> {
			XXX<Any>()
		}.message shouldBe "An operation is not implemented."
		shouldThrow<NotImplementedError> {
			val l = XXX<Long>()
			l + 1
		}.message shouldBe "An operation is not implemented."
	}
})

class KMUKtTestPrintDebug : FunSpec() {
	private val prevOut = System.out
	private val sysOut = ByteArrayOutputStream()

	override fun beforeSpec(description: Description, spec: Spec) {
		System.setOut(PrintStream(sysOut))
	}

	override fun afterSpec(description: Description, spec: Spec) {
		System.setOut(prevOut)
	}

	override fun afterTest(description: Description, result: TestResult) {
		sysOut.reset()
	}

	init {
		test("printDebug -> String") {
			"t".printDebug()
			sysOut.toString().trim() shouldBe "t"
		}

		test("printDebug -> Object") {
			TData("t1").printDebug()
			sysOut.toString().trim() shouldBe "TData(id=t1, l=2)"
		}
	}
}