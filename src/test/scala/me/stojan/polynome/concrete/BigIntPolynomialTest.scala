package me.stojan.polynome.concrete

import org.scalatest._
import me.stojan.polynome.test._

class BigIntPolynomialTest extends PolynomeTest {
  "BigIntPolynomial" should "create [+ 1 * x^1, 0. ]" in {
    val one = BigIntPolynomial(1, 1)

    one.degree should be (1)
    one.coefficient should be (1)
    one.sub.degree should be (-1)
  }

  it should "create [+ 1 * x^2, 0. ]" in {
    val degOne = BigIntPolynomial(2, 1)

    degOne.degree should be (2)
    degOne.coefficient should be (1)
    degOne.sub.degree should be (-1)
  }

  it should "create [+ 1 * x^1, [+ 1 * x^0, 0. ]]" in {
    val poly = BigIntPolynomial(1, 1, BigIntPolynomial(0, 1))

    poly.degree should be (1)
    poly.coefficient should be (1)
    poly.sub.degree should be (0)
    poly.sub.coefficient should be (1)
  }

  it should "add [+ 1x^1, 0.] + [+ 1x^1, 0.]" in {
    val a = BigIntPolynomial(1, 1)
    val add = a + a

    add.degree should be (1)
    add.coefficient should be (2)
    add.sub.degree should be (-1)
  }

  it should "subtract [+ 2x^1, 0.] - [+ 1x^1, 0.]" in {
    val a = BigIntPolynomial(1, 2)
    val b = BigIntPolynomial(1, 1)

    val sub = a - b

    sub.degree should be (1)
    sub.coefficient should be (1)
    sub.sub.degree should be (-1)
  }

  it should "subtract [+ 1x^1, 0.] - [+ 1x^1, 0.] to 0." in {
    val a = BigIntPolynomial(1, 1)
    val sub = a - a

    sub.degree should be (-1)
  }

  it should "negate [+ 1x^1, 0.] to [- 1x^1, 0.]" in {
    val a = BigIntPolynomial(1, 1)
    val neg = -a

    neg.degree should be (a.degree)
    neg.coefficient should be (-a.coefficient)
    neg.sub.degree should be (-1)
  }

  it should "multiply the first term with all other terms (*+) [+ 1x^2, 1x^1, 0.] *+ [+ 1x^1, 0.]" in {
    val a = BigIntPolynomial(2, 1, BigIntPolynomial(1, 1))
    val b = BigIntPolynomial(1, 1)

    val starSum = a *+ b

    starSum should equal (BigIntPolynomial(3, 1, BigIntPolynomial(2, 1)))
  }

  it should "multiply the first term with all other terms (*+) [+ 1x^1, 0.] *+ [+ 1x^2, 1x^1, 0.]" in {
    val a = BigIntPolynomial(1, 1)
    val b = BigIntPolynomial(2, 1, BigIntPolynomial(1, 1))

    val starSum = a *+ b

    starSum should equal (BigIntPolynomial(3, 1))
  }

  it should "multiply the polynomials [+ 1x^2, [+ 1x^1, 0.]] * [+ 1x^2, [+ 1x^1, 0.]]" in {
    val a = BigIntPolynomial(2, 1, BigIntPolynomial(1, 1))

    (a * a) should be (BigIntPolynomial(4, 1, BigIntPolynomial(3, 2, BigIntPolynomial(2, 1))))
  }

  it should "multiply the polynomials [+ 1x^2, [+ 1x^1, 0.]] * [+ 1x^3, [+ 1x^2, [+ 1x^1, 0.]]]" in {
    val a = BigIntPolynomial(2, 1, BigIntPolynomial(1, 1))
    val b = BigIntPolynomial(3, 1, a)

    (a * b) should be (BigIntPolynomial(5, 1, BigIntPolynomial(4, 2, BigIntPolynomial(3, 2, BigIntPolynomial(2, 1)))))
  }

  it should "divide the polynomials [+ 1^x3, [+ 1x^2, [+ 1x^1, 0. ]]] / [+ 1x^1, 0. ]" in {
    val a = BigIntPolynomial(1, 1)
    val b = BigIntPolynomial(3, 1, BigIntPolynomial(2, 1, a))

    val q = BigIntPolynomial(2, 1, BigIntPolynomial(1, 1, BigIntPolynomial(0, 1)))
    val r = BigIntPolynomial()

    (b / a) should be ((q, r))
  }

  it should "divide the polynomials [+ 1x^3, [- 2x^2, [- 4x^0, 0. ]]] / [+ 1x^1, [- 3x^0, 0. ]]" in {
    val a = BigIntPolynomial(3, 1, BigIntPolynomial(2, -2, BigIntPolynomial(0, -4)))
    val b = BigIntPolynomial(1, 1, BigIntPolynomial(0, -3))

    val q = BigIntPolynomial(2, 1, BigIntPolynomial(1, 1, BigIntPolynomial(0, 3)))
    val r = BigIntPolynomial(0, 5)

    (a / b) should be ((q, r))
  }

  it should "divide 0. / [+ 1x^1, 0. ]" in {
    val a = BigIntPolynomial()
    val b = BigIntPolynomial(1, 1)

    val zero = BigIntPolynomial()

    (a / b) should be ((zero, zero))
  }

  it should "divide [+ 1x^1, 0. ] / [+ 1x^2, 0. ]" in {
    val a = BigIntPolynomial(1, 1)
    val b = BigIntPolynomial(2, 1)

    (a / b) should be ((BigIntPolynomial(), a))
  }
}
