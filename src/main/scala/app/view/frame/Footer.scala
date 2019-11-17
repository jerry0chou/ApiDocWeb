package app.view.frame

import com.thoughtworks.binding.dom

object Footer
{
    @dom def render =
    {
        <div>
            <div class="ui vertical footer segment">
                <div class="ui center aligned container">
                    <img src="static/images/logo.png" class="ui centered mini image"/>
                    <div class="ui horizontal  small divided link list">
                        <a class="item" href="#">Api document generator</a>
                    </div>
                </div>
            </div>
        </div>
    }
}
