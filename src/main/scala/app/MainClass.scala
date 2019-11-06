package app

import com.thoughtworks.binding.dom
import org.scalajs.dom.document

object MainClass
{
    def main(args: Array[String]): Unit =
    {
        dom.render(document.body,View.render)
    }
}
