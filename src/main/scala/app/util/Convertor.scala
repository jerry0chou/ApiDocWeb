package app.util

import upickle.default.{macroRW, ReadWriter => RW}

object Convertor
{

    case class Project(projId: Int, projName: String, projDesc: String)

    object Project
    {
        implicit def rw: RW[Project] = macroRW
    }

    case class ProjectList(code: Int, data: List[Project], Msg: String)

    object ProjectList
    {
        implicit def rw: RW[ProjectList] = macroRW
    }

    case class AddOrUpdateMSg(code: Int, data: String, Msg: String)

    object AddOrUpdateMSg
    {
        implicit def rw: RW[AddOrUpdateMSg] = macroRW
    }

}
