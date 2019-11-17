package app.view.frame

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
            case "" => NotFound.render.bind
            case _ => NotFound.render.bind
        }}
        </div>
    }
}
