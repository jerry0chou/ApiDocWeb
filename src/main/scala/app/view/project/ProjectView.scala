package app.view.project

import app.util.Convertor._
import app.util.{Http, Store}
import app.util.Store.ProjectVar
import app.view.frame.Route
import com.thoughtworks.binding.Binding.{Var, Vars}
import com.thoughtworks.binding.dom
import org.scalajs.dom.raw.Event
import upickle.default.{read, write}

import scala.language.experimental.macros
import scala.scalajs.concurrent.JSExecutionContext.Implicits.queue
import scala.util.{Failure, Success}
import app.util.Auto._
import app.view.common.CSS

object ProjectView
{
    val projectList = Vars.empty[ProjectVar]

    def deleteProject(id: Int) =
    {
        Http.post("/project/deleteProject", write[ID](ID(id))).andThen {
            case Success(response) =>
                val str = response.responseText
                println(read[Message](str).Msg)
                val index = projectList.value.map(_.projId.value).indexOf(id)
                projectList.value.remove(index)
            case Failure(exception) =>
                Route.path.value = "exception"
        }
    }

    def AddorEditProject(p: ProjectVar) =
    {
        if (p.projId.value != -1)
            Store.currentProject = ProjectVar(p.projId, p.projName, p.projDesc)
        else
            Store.currentProject = ProjectVar(-1, "", "")
        Route.path.value = "project_op"
    }

    def openProject(p: ProjectVar)={
        Store.currentProject=ProjectVar(p.projId, p.projName, p.projDesc)
        Route.path.value ="module"
    }

    def mounted =
    {
        if (projectList.value.length == 0) {
            Http.get("/project/getProjectList").andThen {
                case Success(response) =>
                    val str = response.responseText
                    read[ProjectList](str).data.map(pro => projectList.value += ProjectVar(pro.projId, pro.projName, pro.projDesc))
                case Failure(exception) =>
                    Route.path.value = "exception"
            }
        }
    }

    @dom def render =
    {
        mounted
        <div>
            {CSS.css.bind}
            <div style="margin-top: 50px"></div>
            <div class="ui container">
                <div class="ui grid ">
                    {for (p <- projectList) yield {
                    <div class="five wide column ">
                        <div class="ui card shadow">
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
                                <button class="ui circular primary eye icon small button" onclick={e:Event=>openProject(p)}><i class="eye icon"></i></button>
                                <button class="ui circular olive edit  small button" onclick={e: Event => AddorEditProject(p)}><i class="edit  icon"></i></button>
                                <button class="ui circular red trash icon small button" onclick={e: Event => deleteProject(p.projId.value)}><i class="trash icon"></i></button>
                            </div>
                        </div>
                    </div>
                }}<div class="five wide column">
                    <div class="ui card">
                        <button class="green ui button" onclick={e: Event => AddorEditProject(ProjectVar(-1, "", ""))}>添加项目</button>
                    </div>
                </div>
                </div>
            </div>
        </div>
    }
}
