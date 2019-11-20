package app.util

import app.util.Convertor.Project
import com.thoughtworks.binding.Binding.Var
import app.util.Auto._

object Store
{

    // for Project turn into EditProject
    case class ProjectVar(projId: Var[Int], projName: Var[String], projDesc: Var[String])
    {
        override def toString: String = projId.value + projName.value + projDesc.value
    }

    var currentProject = ProjectVar(-1, "", "")
}
