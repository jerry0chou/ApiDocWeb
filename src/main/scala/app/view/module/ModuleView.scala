package app.view.module

import app.util.Store.{ModuleApiListVar, SimpleApiVar}
import app.view.frame.Route
import com.thoughtworks.binding.Binding.{Var, Vars}
import com.thoughtworks.binding.dom
import org.scalajs.dom.raw.Event
import app.util.Auto._
import app.util.Convertor.{ID, ModApiResult, Module}
import app.util.Http
import upickle.default.{read, write}
import app.util.Store.currentProject

import scala.collection.mutable.ArrayBuffer
import scala.scalajs.concurrent.JSExecutionContext.Implicits.queue
import scala.util.{Failure, Success}

object ModuleView
{
    val moduleActive = Var("")
    val modApiList = Vars.empty[ModuleApiListVar]

    val count=Var(0)
    def addOrEditModule(modId: Int) =
    {
        Route.modApiPath.value = "module_op"
    }

    def mounted =
    {
        count.value+=1
        modApiList.value.clear()
        val id = ID(currentProject.projId.value)
        Http.post("/module/getModApi", write(id)).andThen {
            case Success(response) =>
                val str = response.responseText
                val maList = read[ModApiResult](str).data
                maList.map(e => {
                    val api = Vars.empty[SimpleApiVar]
                    e.apiList.map(a => api.value += SimpleApiVar(a.apiId, a.apiName))
                    modApiList.value += ModuleApiListVar(e.modId, e.modName, api)
                })
            case Failure(exception)=>
                Route.modApiPath.value = "exception"
        }
    }

    @dom def css = <style>
        {"""
            .moduleClass {
            margin-left: 20px;
          }

          .ui.grid > .row{
                    position: relative;
                    display: -webkit-box;
                    display: -ms-flexbox;
                    display: flex;
                    -webkit-box-orient: horizontal;
                    -webkit-box-direction: normal;
                    -ms-flex-direction: row;
                    flex-direction: row;
                    -ms-flex-wrap: wrap;
                    flex-wrap: wrap;
                    -webkit-box-pack: inherit;
                    -ms-flex-pack: inherit;
                    justify-content: inherit;
                    -webkit-box-align: stretch;
                    -ms-flex-align: stretch;
                    align-items: stretch;
                    width: 100% !important;
                    padding: 0rem;
                    padding-top: 1rem;
                    padding-right: 0rem;
                    padding-bottom: 1rem;
                    padding-left: 0rem;
                    padding-top: 3px;
                    padding-bottom: 3px;

          }

            """}
    </style>

    @dom def render =
    {
        mounted
        <div>
            {count.bind.toString}+{currentProject.projId.bind.toString}
            {css.bind}<div class="four wide column moduleClass">
            <div class="ui mini icon input">
                <input type="text" placeholder="Search Api"/>
                <button class="ui mini teal icon button">
                    <i class="search icon"></i>
                </button>
                <button class="ui mini teal icon button" onclick={e: Event => addOrEditModule(-1)}>
                    <i class="plus icon"></i>
                </button>
            </div>
        </div>
            <div class="ui grid">
                <div class="four wide column moduleClass">
                    <div class="ui styled fluid accordion">
                        {for (modApi <- modApiList) yield {
                        <div>
                            <div class="title active">
                                <div class="ui grid">
                                    <div class="fourteen wide column">
                                        <a class="ui teal ribbon large label">
                                            {modApi.modName.bind}
                                        </a>
                                    </div>
                                    <div class="two wide column">
                                        <i class="plus square teal large icon"></i>
                                    </div>
                                </div>
                            </div>
                            <div class="content active">
                                <div class="ui grid">
                                    {for (api <- modApi.apiList) yield {
                                    <div class="row">
                                        <div class="fourteen wide column">
                                            <div class="ui blue circular large label">
                                                {api.apiName.bind}
                                            </div>
                                        </div>
                                        <div class="two wide column">
                                            <i class="plus circle blue large icon"></i>
                                        </div>
                                    </div>
                                }}
                                </div>
                            </div>
                        </div>
                    }}
                    </div>
                </div>

                <div class="eleven wide column">
                    {Route.modApiRender.bind}
                </div>
            </div>
        </div>
    }
}
