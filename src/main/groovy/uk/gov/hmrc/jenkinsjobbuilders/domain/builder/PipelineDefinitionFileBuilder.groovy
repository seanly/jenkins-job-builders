package uk.gov.hmrc.jenkinsjobbuilders.domain.builder

import javaposse.jobdsl.dsl.DslFactory
import javaposse.jobdsl.dsl.Job

class PipelineDefinitionFileBuilder extends PipelineBuilder {

    private String filePath

    PipelineDefinitionFileBuilder(String name, String description) {
        super(name, description)
    }

    PipelineBuilder withFilePath(String filePath) {
        this.filePath = filePath
        this
    }

    @Override
    Job build(DslFactory dslFactory) {
        job = super.build(dslFactory)

        job.definition {
            cps {
                script(dslFactory.readFileFromWorkspace(filePath))
                sandbox()
            }
        }
    }
}
