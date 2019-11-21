package app.view.common

import com.thoughtworks.binding.dom

object CSS
{
    @dom def css = <style>
        {"""
            .shadow {
            border: 5px;
            padding: 5px 5px 5px 5px;
            border-radius: 5px;
            -moz-border-radius: 5px; /* Old Firefox */
            background: #ffffff;
            margin-top: 5px;
            margin-bottom: 5px;
            box-shadow: 0px 0px 5px 5px #e2e2e4;
          }

          .shadow:hover {
            -webkit-box-shadow: #ccc 0px 0px 6px 6px;
            -moz-box-shadow: #ccc 0px 0px 6px 6px;
            box-shadow: #ccc 0px 0px 6px 6px;
          }
            """
        }
    </style>
}
