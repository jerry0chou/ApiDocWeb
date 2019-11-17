package app.util

import upickle.default.{macroRW, ReadWriter => RW}

object convert
{

    case class Person(name: String)

    object Person
    {
        implicit def rw: RW[Person] = macroRW
    }

    case class Project(projId: Int, projName: String, projDesc: String)

    object Project
    {
        implicit def rw: RW[Project] = macroRW
    }

    case class ProjectSet(code: Int, data: List[Project], Msg: String)

    object ProjectSet
    {
//        implicit def rw[T: RW]: RW[ProjectSet[List[Project]]]= macroRW
        implicit def rw: RW[ProjectSet] = macroRW
    }

}
