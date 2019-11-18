package app.view.frame

import app.view.common.{NetException, NotFound}
import app.view.project.{EditProject, ProjectView}
import com.thoughtworks.binding.Binding.Var
import com.thoughtworks.binding.dom

object Route
{
    val path = Var("project")

    @dom def render =
    {
        <div>
            {path.bind match {
            case "project" => ProjectView.render.bind
            case "project_edit" => EditProject.render.bind
            case "exception" => NetException.render.bind
            case _ => NotFound.render.bind
        }}
        </div>
    }
}
