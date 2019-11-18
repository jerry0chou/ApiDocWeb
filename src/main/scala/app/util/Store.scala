package app.util

import com.thoughtworks.binding.Binding.Var

object Store
{

    // for Project turn into EditProject
    case class ProjectVar( projId: Var[Int], projName: Var[String], projDesc: Var[String])

    var currentProject = ProjectVar(Var(-1), Var(""), Var(""))
}
