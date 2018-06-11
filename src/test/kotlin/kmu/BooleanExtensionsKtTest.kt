package kmu

import io.kotlintest.data.forall
import io.kotlintest.shouldBe
import io.kotlintest.shouldThrow
import io.kotlintest.specs.FunSpec
import io.kotlintest.tables.row

class BooleanExtensionsKtTest : FunSpec({
	test("toBoolean -> Pass") {
		forall(
			row("true", true),
			row("True", true),
			row("y", true),
			row("Y", true),
			row("yes", true),
			row("Yes", true),
			row("n", false),
			row("false", false),
			row("False", false),
			row("N", false),
			row("No", false),
			row("no", false)
		) { value, expected ->
			value.toBoolean() shouldBe expected
		}
	}

	test("toBoolean -> Fail") {
		arrayOf(
			" y",
			"y ",
			" n ",
			"n ",
			"e"
		).forEach { value ->
			shouldThrow<IllegalArgumentException> {
				value.toBoolean()
			}.message shouldBe "unsupported value: '$value'"
		}
	}
})