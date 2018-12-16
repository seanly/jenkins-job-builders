package uk.gov.hmrc.jenkinsjobbuilders.domain.builder

import javaposse.jobdsl.dsl.DslFactory
import javaposse.jobdsl.dsl.Job

class PipelineDefinitionScriptBuilder extends PipelineBuilder {

    private String script
    PipelineDefinitionScriptBuilder(String name, String description) {
        super(name, description)
    }

    PipelineBuilder withScript(String script) {
        this.script = script
        this
    }

    @Override
    Job build(DslFactory dslFactory) {

        job = super.build()

        job.definition {
            cps {
                script(script)
                sandbox()
            }
        }
    }

}
