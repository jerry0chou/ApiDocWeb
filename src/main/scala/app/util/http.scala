package app.util

import org.scalajs.dom.ext.Ajax

object http
{
    // data: case class convert to json string by implicit
    def post(url: String, data: String) =
        Ajax.post(url, data = data, headers = Map("Content-Type" -> "application/json"))

    def get(url: String) =
        Ajax.get(url, headers = Map("Content-Type" -> "application/json"))
}
