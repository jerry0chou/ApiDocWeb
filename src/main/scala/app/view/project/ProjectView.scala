package app.view.project

import app.util.Convertor._
import app.util.Http
import app.view.frame.Route
import com.thoughtworks.binding.Binding.{Var, Vars}
import com.thoughtworks.binding.dom
import org.scalajs.dom.raw.Event
import upickle.default.read

import scala.language.experimental.macros
import scala.scalajs.concurrent.JSExecutionContext.Implicits.queue
import scala.util.Success

object ProjectView
{

    case class Project(projId: Var[Int], projName: Var[String], projDesc: Var[String])

    val projectList = Vars.empty[Project]

    case class Human(name: String)

    val editProject={
        e:Event=>
            Route.path.value="project_edit"
    }

    def mounted =
    {
        projectList.value.clear()
        Http.get("/project/getProjectList").andThen {
            case Success(response) =>
                val str = response.responseText
                read[ProjectList](str).data.map(pro => projectList.value += Project(Var(pro.projId), Var(pro.projName), Var(pro.projDesc)))
            //            case Failure(exception)=>
        }
    }

    @dom def render =
    {
        mounted
        <div>
            <div style="margin-top: 50px"></div>
            <div class="ui container">
                <div class="ui grid">
                    {for (p <- projectList) yield {
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
                                <button class="ui  button" onclick={editProject}>修改项目</button>
                            </div>
                        </div>
                    </div>
                }}<div class="five wide column">
                    <div class="ui card">
                        <button class="green ui button">添加项目</button>
                    </div>
                </div>
                </div>
            </div>
        </div>
    }
}
