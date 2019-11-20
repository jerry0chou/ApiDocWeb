package app.util

import com.thoughtworks.binding.Binding.Var

object Auto
{
    implicit def intToVarInt(i: Int) = Var(i)

    implicit def strToVarStr(str: String) = Var(str)
}
