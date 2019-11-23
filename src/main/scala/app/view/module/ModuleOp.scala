package app.view.module

import app.util.Convertor
import app.util.Convertor.{Module, SingleModule}
import app.util.Http
import app.util.Store.{ModuleApiListVar, ModuleVar, currentModule, currentProject}
import app.view.frame.Route
import com.thoughtworks.binding.Binding.{Var, Vars}
import com.thoughtworks.binding.dom
import org.scalajs.dom.html.Input
import org.scalajs.dom.raw.Event
import upickle.default.{read, write}
import app.util.Auto._

import scala.scalajs.concurrent.JSExecutionContext.Implicits.queue
import scala.util.{Failure, Success}

object ModuleOp
{
    val msg = Var("")
    val disable = Var(false)

    val submitModule = {
        e: Event =>
            e.preventDefault()
            if (currentModule.modName.value == "")
                msg.value="please fill module info"

            else {

                val mStr = Module(currentModule.modId.value,currentProject.projId.value, currentModule.modName.value, currentModule.modDesc.value)
               println(mStr)
                Http.post("/module/addOrEditModule", write[Module](mStr)).andThen {
                    case Success(response) =>
                        val str = response.responseText
                        println(str)
                        val json=read[SingleModule](str)
                        val module = json.data
                        msg.value=json.Msg
                        val modApiListVar=ModuleApiListVar(module.modId,module.modName,Vars.empty)
                        if (currentModule.modId.value == -1)
                            {
                                ModuleView.modApiList.value += modApiListVar
                                disable.value = true
                            }
                        else {
                            val index = ModuleView.modApiList.value.map(_.modId.value).indexOf(currentModule.modId.value)
                            ModuleView.modApiList.value(index)=modApiListVar
                        }
                    case Failure(exception) =>
                        Route.modApiPath.value = "exception"
                }
            }
    }

    @dom def render =
    {
        msg.value = ""
        disable.value=false
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
                            <label>Module Name</label>
                            <input type="text" name="Module-name" placeholder="Module Name" value={currentModule.modName.bind} oninput={e: Event => currentModule.modName.value = e.currentTarget.asInstanceOf[Input].value}/>
                        </div>
                        <div class="field">
                            <label>Project Description</label>
                            <input type="text" name="module description" placeholder="Module Description" value={currentModule.modDesc.bind} oninput={e: Event => currentModule.modDesc.value = e.currentTarget.asInstanceOf[Input].value}/>
                        </div>
                        <button class="ui blue button" style:display={if (disable.bind == false) "" else "none"} onclick={submitModule}>Submit</button>
                        <button class="ui teal button" onclick={e: Event => e.preventDefault(); Route.modApiPath.value = "module_analysis"}>Back</button>
                    </form>
                </div>
            </div>
        </div>
    }
}
