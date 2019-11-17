package app.view.frame

import com.thoughtworks.binding.dom
import org.scalajs.dom.raw.Event

object Header
{
    val backToHome = {
        e: Event =>
            Route.path.value = "project"
    }

    @dom def css = <style>
        {"""
              body {
                background-color: #FFFFFF;
              }
              .ui.menu .item img.logo {
                margin-right: 1.5em;
              }
              .main.container {
                margin-top: 7em;
              }
              .ui.footer.segment {
                margin: 5em 0em 0em;
                padding: 5em 0em;
              }
              .paddingHeader{
                margin-bottom: 50px;
              }
            """}
    </style>

    @dom def render =
    {
        <div>
            {css.bind}<div class="ui fixed  menu">
            <a href="#" class="header item" onclick={backToHome}>
                <img class="logo" src="static/images/logo.png"/>
                主页
            </a>
            <a href="#" class="item">Home</a>
        </div>
            <div style="margin-bottom: 80px"></div>
        </div>
    }
}
