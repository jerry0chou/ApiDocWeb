package app.util

import com.thoughtworks.binding.Binding.{Var, Vars}
import app.util.Auto._

object Store
{

    case class ProjectVar(projId: Var[Int], projName: Var[String], projDesc: Var[String])

    var currentProject = ProjectVar(-1, "", "")

    case class ModuleVar(modId: Var[Int], modName: Var[String], modDesc: Var[String])

    var currentModule = ModuleVar(-1, "", "")

    case class SimpleApiVar(apiId: Var[Int], apiName: Var[String])

    case class ModuleApiListVar(modId: Var[Int], modName: Var[String], apiList: Vars[SimpleApiVar])

    var currentModuleApiListVar = ModuleApiListVar(-1, "", Vars.empty)
}
