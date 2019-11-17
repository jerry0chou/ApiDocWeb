package app.view.frame

import com.thoughtworks.binding.dom

object All
{
    @dom def render={
       <div>
           {Header.render.bind}
           {Route.render.bind}
           {Footer.render.bind}
       </div>
    }
}
