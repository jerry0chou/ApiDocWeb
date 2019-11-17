import com.thoughtworks.binding.Binding

package object app
{
    implicit def makeIntellijHappy[T<:org.scalajs.dom.raw.Node](x: scala.xml.Node): Binding[T] =
        throw new AssertionError("This should never execute.")
}