package app.util

import upickle.default.{macroRW, ReadWriter => RW}

object Convertor
{

    case class ID(id: Int)

    object ID
    {
        implicit def rW: RW[ID] = macroRW
    }

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

    case class Message(code: Int, data: String, Msg: String)

    object Message
    {
        implicit def rw: RW[Message] = macroRW
    }

    case class SingleProject(code: Int, data: Project, Msg: String)

    object SingleProject
    {
        implicit def rw: RW[SingleProject] = macroRW
    }
}
