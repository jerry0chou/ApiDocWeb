package app

import com.thoughtworks.binding.Binding.Var
import com.thoughtworks.binding.dom
import org.scalajs.dom.ext.Ajax
import org.scalajs.dom.raw.Event
import upickle.default.{macroRW, read, write, ReadWriter => RW}
import scala.scalajs.concurrent.JSExecutionContext.Implicits.queue
import scala.util.Success

object View
{

    case class Person(name: String)

    object Person
    {
        implicit def rw: RW[Person] = macroRW
    }

    val person = new Person("jerry chou fgfg fgfg")

    case class Project(projId: Int, projName: String, projDesc: String)

    object Project
    {
        implicit def rw: RW[Project] = macroRW
    }

    val responseStr = Var("")
    val clickEvent = {
        event: Event =>
            val personJson = write[Person](person)
            println(personJson)
            val result = Ajax.post("/project/projects", data = personJson, headers = Map("Content-Type" -> "application/json"))
            result.andThen {
                case Success(response) => {
                    val str = response.responseText
                    println(str)
                    val projectList = read[List[Project]](str)
                    responseStr.value = projectList.mkString(",")
                }
            }
    }

    @dom def render =
    {
        <div>
            <div style="margin-top: 50px"></div>
            <div class="ui container">
                <div class="ui grid">
                    <div class="four wide column">
                        <div class="ui card">
                        <div class="content">
                            <div class="header">Project Timeline</div>
                        </div>
                        <div class="content">
                            <h4 class="ui sub header">Activity</h4>
                        </div>
                        <div class="extra content">
                            <button class="ui primary button">进入项目</button>
                        </div>
                        </div>
                    </div>
                    <div class="four wide column">
                         two
                    </div>
                        <div class="four wide column">three</div>
                        <div class="four wide column">four</div>
                        <div class="four wide column">
                        </div>
                        <div class="four wide column">six</div>
                        <div class="four wide column">seven</div>
                        <div class="four wide column">eight</div>
                        <div class="four wide column">eight</div>
                </div>
            </div>
        </div>
    }
}
