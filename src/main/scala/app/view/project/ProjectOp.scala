package app.view.project

import app.util.Convertor.{Message, Project, SingleProject}
import app.util.Http
import app.util.Store.{ProjectVar, currentProject}
import com.thoughtworks.binding.dom
import org.scalajs.dom.html.Input
import org.scalajs.dom.raw.Event
import app.view.frame.Route
import com.thoughtworks.binding.Binding.Var
import app.util.Auto._
import scala.scalajs.concurrent.JSExecutionContext.Implicits.queue
import scala.util.{Failure, Success}
import upickle.default.{read, write}

object ProjectOp
{
    val msg = Var("")
    val submitProject = {
        e: Event =>
            e.preventDefault()
            if (currentProject.projName.value == "") {
                msg.value = "please fill project name"
            }
            else {
                val pStr = Project(currentProject.projId.value, currentProject.projName.value, currentProject.projDesc.value)
                Http.post("/project/addOrEditProject", write(pStr)).andThen {
                    case Success(response) =>
                        val str = response.responseText
                        val json=read[SingleProject](str)
                        val project = json.data
                        msg.value=json.Msg
                        val projectVar=ProjectVar(project.projId, project.projName, project.projDesc)
                        if (currentProject.projId.value == -1)
                            ProjectView.projectList.value += projectVar
                        else {
                            val index = ProjectView.projectList.value.map(_.projId.value).indexOf(currentProject.projId.value)
                            ProjectView.projectList.value(index)=projectVar
                        }
                    case Failure(exception) =>
                        Route.path.value = "exception"
                }
            }
    }

    @dom def render =
    {
        msg.value = ""
        <div>
            <div class="ui grid">
                <div class="four wide column"></div>
                <div class="eight wide column">
                    <div class="ui green label" style:display={if (msg.bind != "") "" else "none"}>
                        {msg.bind}
                    </div>
                    <br/>
                    <form class="ui form">
                        <div class="field">
                            <label>Project Name</label>
                            <input type="text" name="priject-name" placeholder="Project Name" value={currentProject.projName.bind} oninput={e: Event => currentProject.projName.value = e.currentTarget.asInstanceOf[Input].value}/>
                        </div>
                        <div class="field">
                            <label>Project Description</label>
                            <input type="text" name="project description" placeholder="Project Description" value={currentProject.projDesc.bind} oninput={e: Event => currentProject.projDesc.value = e.currentTarget.asInstanceOf[Input].value}/>
                        </div>
                        <button class="ui blue button" onclick={submitProject}>Submit</button>
                        <button class="ui green button" onclick={e: Event => e.preventDefault(); Route.path.value = "project"}>Back</button>
                    </form>
                </div>
            </div>
        </div>
    }
}
