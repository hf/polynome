package me.stojan.polynome

import org.scalatest._
import me.stojan.polynome.test._

class NullPolynomialTest extends PolynomeTest {
  "NullPolynomial" should "have (-1) as a degree" in {
    (NullPolynomial().degree) should be (-1)
  }

  it should "throw NotImplemented for coefficient" in {
    a [scala.NotImplementedError] should be thrownBy {
      NullPolynomial().coefficient
    }
  }

  it should "0. + X == X" in {
    val X = mock[Polynomial[Any]]
    (NullPolynomial() + X) should be (X)
  }

  it should "0. - X == -X" in {
    val X = mock[Polynomial[Any]]
    (X.unary_- _).expects.once

    NullPolynomial() - X
  }

  it should "0. *+ X == 0." in {
    val X = mock[Polynomial[Any]]
    (NullPolynomial() *+ X) should be (NullPolynomial())
  }

  it should "0. * X == 0." in {
    val X = mock[Polynomial[Any]]
    (NullPolynomial() * X) should be (NullPolynomial())
  }

  it should "0. -> sub == 0." in {
    (NullPolynomial().sub) should be (NullPolynomial())
  }
}
