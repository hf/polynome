package me.stojan.polynome.concrete

import me.stojan.polynome._

private sealed case class BIPolynomial(
  degree: Long, coefficient: BigInt, override val sub: Polynomial[BigInt])
  extends Polynomial[BigInt]
{
  override def +(p: Polynomial[BigInt]): Polynomial[BigInt] =
    if (degree < p.degree) {
      p + this
    } else {
      if (degree == p.degree) {
        val c = coefficient + p.coefficient

        if (c == 0) {
          sub + p.sub
        } else {
          BIPolynomial(degree, coefficient + p.coefficient, sub + p.sub)
        }
      } else {
        BIPolynomial(degree, coefficient, sub + p)
      }
    }

  override def -(p: Polynomial[BigInt]): Polynomial[BigInt] =
    this + (- p)

  override def *+(p: Polynomial[BigInt]): Polynomial[BigInt] =
    BIPolynomial(degree + p.degree, coefficient * p.coefficient, this.sub *+ p)

  override def unary_-(): Polynomial[BigInt] =
    BIPolynomial(degree, - coefficient, - sub)
}

private object BigIntNullPolynomial extends NullPolynomial[BigInt]

object BigIntPolynomial {
  def apply(degree: Long, coefficient: BigInt, sub: Polynomial[BigInt] = BigIntNullPolynomial): Polynomial[BigInt] =
    BIPolynomial(degree, coefficient, sub)
}
