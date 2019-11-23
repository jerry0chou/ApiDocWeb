package app.view.frame

import app.view.common.{NetException, NotFound}
import app.view.module.{ModuleAnalysis, ModuleOp, ModuleView}
import app.view.project.{ProjectOp, ProjectView}
import com.thoughtworks.binding.Binding.Var
import com.thoughtworks.binding.dom

object Route
{
    val path = Var("project")

    val modApiPath=Var("module_analysis")


    @dom def render =
    {
        <div>
            {path.bind match {
            case "project" => ProjectView.render.bind
            case "project_op" => ProjectOp.render.bind
            case "module" => ModuleView.render.bind
            case "exception" => NetException.render.bind
            case _ => NotFound.render.bind
        }}
        </div>
    }

    @dom def modApiRender={
        <div>
            {
            modApiPath.bind match {
            case "module_op" => ModuleOp.render.bind
            case "module_analysis"=> ModuleAnalysis.render.bind
            case "exception" => NetException.render.bind
            case _ => NotFound.render.bind
        }}
        </div>
    }
}
