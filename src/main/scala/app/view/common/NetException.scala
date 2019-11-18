package app.view.common

import app.view.frame.Route
import com.thoughtworks.binding.dom
import org.scalajs.dom.raw.Event
object NetException
{
    @dom def render={
        <div>
            <div class="ui center aligned container">
                <img src="static/images/exception.png" class="ui centered image"/>
                <br/>
                <div class="ui red label">network went something wrong</div>
                <br/>
                <br/>
                <button class="ui blue button" onclick={e:Event=>Route.path.value="project"}>refresh</button>
            </div>
        </div>
    }
}
