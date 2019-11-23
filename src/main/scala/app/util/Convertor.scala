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

    case class Module(modId: Int, projId: Int, modName: String, modDesc: String)

    object Module
    {
        implicit def rw: RW[Module] = macroRW
    }

    case class SingleModule(code: Int, data: Module, Msg: String)

    object SingleModule
    {
        implicit def rw: RW[SingleModule] = macroRW
    }


    //
    case class SimpleApi(apiId: Int, apiName: String)

    object SimpleApi
    {
        implicit def rw: RW[SimpleApi] = macroRW
    }

    case class ModApiList(modId: Int, modName: String, apiList: List[SimpleApi])

    object ModApiList
    {
        implicit def rw: RW[ModApiList] = macroRW
    }

    case class ModApiResult(code: Int, data: List[ModApiList], Msg: String)

    object ModApiResult
    {
        implicit def rw: RW[ModApiResult] = macroRW
    }

}
