Polynome
========

Polynome is a simple implementation of polynomials (and their arithmetic) in
Scala. It solves the problem of having different types of coefficients, namely
`BigInt`s or even implementing things like polynomial finite fields.

It uses a recursive, cons-like structure. Terms are immutable and operations
are recursive.

**Why Scala?**

Turns out you can cleanly and in a type-safe way implement a lot of mathematical
structures in Scala. I wish scientific papers were written in Scala.

## Example

```scala
import me.stojan.polynome._
import me.stojan.polynome.concrete._

val a = BigIntPolynomial(degree = 1, coefficient = 1)
// a: Polynomial[BigInt] == [+ 1x^1, 0. ]

val b = BigIntPolynomial(degree = 2, coefficient = 1, a)
// b: Polynomial[BigInt] == [+ 1x^2, [+ 1x^1, 0. ]]

val sum = a + b
// sum == [+ 1x^2, [+ 2x^1, 0. ]]
```

The tests are pretty readable, too.

## Structure

Polynomials should inherit from `Polynomial[V]`. That trait is basically a
cons on steroids. Essentially, it represents a polynomial term with a pointer
to terms of a lesser degree. 0-coefficient terms are not valid and should not be
in the structure. It must terminate with an instance of the special case class
`NullPolynomial[V]`.

Implementors may want to make it a singleton instance, although this is not
required.

It is important to mention that `NullPolynomial` has a degree of -1 and its
methods should really be left alone. They are used to terminate the recursions.

### Interpreting the String

It's simple.

```
 + 1x^3   + 1x^2   + 1x^1   + 1x^0  + 0
[+ 1x^3, [+ 1x^2, [+ 1x^1, [+ 1x^0, 0. ]]]]
```

## License

Copyright &copy; 2015 Stojan Dimitrovski

All code contained herein is licensed under the
[MIT License](http://opensource.org/licenses/MIT). See the full text in the
included `LICENSE.txt` file.
