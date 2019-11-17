package app

import app.view.frame.All
import com.thoughtworks.binding.dom
import org.scalajs.dom.document

object MainApp
{
    def main(args: Array[String]): Unit =
    {
        dom.render(document.body,All.render)
    }
}
