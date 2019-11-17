package app.view.frame

import com.thoughtworks.binding.dom

object NotFound
{
    @dom def render={
        <div>
            <div class="ui center aligned container">
                <img src="static/images/404.png" class="ui centered image"/>
            </div>
        </div>
    }
}
