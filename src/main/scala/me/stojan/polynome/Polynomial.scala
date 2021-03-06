package me.stojan.polynome

/**
 * A polynomial with `V` as its coefficient. Polynomials are a recursive
 * structure. They never contain 0-coefficient terms. (Those terms should be
 * removed by the concrete implementation when generated.)
 *
 * At the end of the chain there is a special term / polynomial called the
 * zero polynomial. It is the default value in `sub`.
 */
trait Polynomial[V] {
  /**
   * All degrees are positive or 0, except for the terminal 0's which is -1.
   */
  def degree: Long

  /**
   * All terms have a coefficient except for terminal 0's.
   */
  def coefficient: V

  /**
   * Adds two polynomials together.
   */
  def +(p: Polynomial[V]): Polynomial[V]

  /**
   * Subtracts two polynomials together.
   */
  def -(p: Polynomial[V]): Polynomial[V] = this + (-p)

  /**
   * Multiplies the highest term of this polynomial with all of the terms of
   * `p`.
   */
  def *+(p: Polynomial[V]): Polynomial[V]

  /**
   * Multiplies two polynomials together.
   *
   * Uses `*+` and `+` to compute the multiplication.
   */
  def *(p: Polynomial[V]): Polynomial[V] = (this.sub * p) + (p *+ this)

  /**
   * Divides the leading terms of this and p.
   *
   * If the coefficients of the division are 0, this must return NonTerm.
   */
  def /-(p: Polynomial[V]): Polynomial[V]

  /**
   * Divides this and p using recursive long division.
   *
   * Returns a tuple of the quotient (at _1) and the remainder (at _2).
   *
   * Division with 0 will return (0, 0).
   */
  def /(p: Polynomial[V]): (Polynomial[V], Polynomial[V]) =
    if (degree != -1 && degree >= p.degree) {
      val t = this /- p

      if (null == t) { // coefficients were 0
        (NullPolynomial[V](), this)
      } else {
        val r = this - (t * p)
        val ret = r / p
        (t + ret._1, ret._2)
      }
    } else {
      (NullPolynomial[V](), this)
    }

  /**
   * Unary negation: Negates all of the terms of this polynomial.
   */
  def unary_-(): Polynomial[V]

  /**
   * Shows a representation of the current polynomial.
   */
  override def toString: String =
    "[+ " + coefficient + "x^" + degree + ", " + sub.toString + "]"

  /**
   * This polynomial's sub-polynomial, i.e. the first term with a lesser degree
   * than this.
   *
   * By default this constitutes the 0/NullPolynomial and is used extensively as
   * a recursion terminator. Make sure that at the end of the polynomial term
   * structure, a NullPolynomial instance can be found.
   */
  def sub: Polynomial[V] = NullPolynomial[V]()

}

/**
 * A terminating NullPolynomial which represents 0.
 */
case class NullPolynomial[V]() extends Polynomial[V] {
    /**
   * 0's degree is -1.
   */
  override def degree: Long = -1

  /**
   * 0's coefficient is NotImplemented.
   */
  override def coefficient = ???

  /**
   * 0 + p is p.
   */
  override def +(p: Polynomial[V]): Polynomial[V] = p

  /**
   * 0 - p is -p.
   */
  override def -(p: Polynomial[V]): Polynomial[V] = - p

  /**
   * p *+ 0 = 0.
   */
  override def *+(p: Polynomial[V]): Polynomial[V] = this

  /**
   * p * 0 = 0.
   */
  override def *(p: Polynomial[V]): Polynomial[V] = this

  /**
   * 0 /- p = 0.
   */
  override def /-(p: Polynomial[V]): Polynomial[V] = this

  /**
   * 0 / p = (0., 0.)
   */
  override def /(p: Polynomial[V]): (Polynomial[V], Polynomial[V]) = (this, this)

  /**
   * (-0) = 0.
   */
  override def unary_-(): Polynomial[V] = this

  /**
   * 0 + 0 + ... + 0 = 0.
   */
  override def sub: Polynomial[V] = this

  override def toString: String = "0."
}
