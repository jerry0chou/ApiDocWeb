package app.view.module

import com.thoughtworks.binding.dom

object ModuleAnalysis
{
    @dom def render={
        <div class="ui two column centered grid">
            <div class="column">
                <div class="ui  statistics">
                    <div class="red statistic">
                        <div class="value">
                            27598
                        </div>
                        <div class="label">
                            Module
                        </div>
                    </div>
                    <div class="green statistic">
                        <div class="value">
                            144521
                        </div>
                        <div class="label">
                            Api
                        </div>
                    </div>
                </div>

            </div>

        </div>

    }
}
