package app.view

import app.util.http
import app.util.convert._
import com.thoughtworks.binding.Binding.{Var, Vars}
import com.thoughtworks.binding.dom
import upickle.default.{read, write}
import scala.scalajs.concurrent.JSExecutionContext.Implicits.queue
import scala.util.Success
import scala.language.experimental.macros
object ProjectView
{
    val person = Person("jerry ")

    case class Project(projId: Var[Int], projName: Var[String], projDesc: Var[String])

    val projets = Vars.empty[Project]

    case class Human(name: String)

    def mount =
    {
        // write[Person](person)
        http.post("/project/projects", write(person)).andThen {
            case Success(response) => {
                val str = response.responseText
                read[ProjectSet](str).data.map(pro => projets.value += Project(Var(pro.projId), Var(pro.projName), Var(pro.projDesc)))
            }
        }
    }

    @dom def render =
    {
        mount
        <div>
            <div style="margin-top: 50px"></div>
            <div class="ui container">
                <div class="ui grid">
                    {for (p <- projets) yield {
                    <div class="five wide column">
                        <div class="ui card">
                            <div class="content">
                                <div class="header">
                                    {p.projName.bind}
                                </div>
                            </div>
                            <div class="content">
                                <h4 class="ui sub header">
                                    {p.projDesc.bind}
                                </h4>
                            </div>
                            <div class="extra content">
                                <button class="ui primary button">进入项目</button>
                            </div>
                        </div>
                    </div>
                }}
                </div>
            </div>
        </div>
    }
}
