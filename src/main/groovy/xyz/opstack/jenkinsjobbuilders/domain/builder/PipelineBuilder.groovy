package xyz.opstack.jenkinsjobbuilders.domain.builder

import javaposse.jobdsl.dsl.DslFactory
import javaposse.jobdsl.dsl.Job
import uk.gov.hmrc.jenkinsjobbuilders.domain.builder.Builder
import uk.gov.hmrc.jenkinsjobbuilders.domain.parameters.Parameter
import uk.gov.hmrc.jenkinsjobbuilders.domain.trigger.Trigger
import uk.gov.hmrc.jenkinsjobbuilders.domain.variable.EnvironmentVariable

import static java.util.Arrays.asList
import static uk.gov.hmrc.jenkinsjobbuilders.domain.wrapper.EnvironmentVariablesWrapper.environmentVariablesWrapper

abstract class PipelineBuilder implements Builder<Job> {

    private final String name
    private final String description
    private final List<Parameter> parameters = []
    private final List<EnvironmentVariable> environmentVariables = []
    private final List<Trigger> triggers = []
    private int daysToKeep = -1
    private int numToKeep = -1
    private String environmentVariablesFile
    private String environmentVariablesScriptContent = ''
    private String environmentVariablesGroovyScript = ''
    private boolean concurrentBuilds = false
    private boolean disabled = false

    PipelineBuilder(String name, String description) {
        this.name = name
        this.description = description
    }

    PipelineBuilder withLogRotator(int daysToKeep, int numToKeep) {
        this.daysToKeep = daysToKeep
        this.numToKeep = numToKeep
        this
    }

    PipelineBuilder withTriggers(Trigger ... triggers) {
        this.triggers.addAll(triggers)
        this
    }

    PipelineBuilder withConcurrentBuilds() {
        this.concurrentBuilds = true
        this
    }

    PipelineBuilder withDisabled() {
        this.disabled = true
        this
    }

    PipelineBuilder withParameters(Parameter ... parameters) {
        withParameters(asList(parameters))
        this
    }

    PipelineBuilder withParameters(List<Parameter> parameters) {
        this.parameters.addAll(parameters)
        this
    }

    PipelineBuilder withEnvironmentVariablesFile(String environmentVariablesFile) {
        this.environmentVariablesFile = environmentVariablesFile
        this
    }

    PipelineBuilder withEnvironmentVariablesScriptContent(String environmentVariablesScriptContent) {
        this.environmentVariablesScriptContent = environmentVariablesScriptContent
        this
    }

    PipelineBuilder withEnvironmentVariablesGroovyScript(String environmentVariablesGroovyScript) {
        this.environmentVariablesGroovyScript = environmentVariablesGroovyScript
        this
    }

    PipelineBuilder withEnvironmentVariables(EnvironmentVariable ... environmentsVariables) {
        withEnvironmentVariables(asList(environmentsVariables))
    }

    PipelineBuilder withEnvironmentVariables(List<EnvironmentVariable> environmentVariables) {
        this.environmentVariables.addAll(environmentVariables)
        this
    }

    @Override
    Job build(DslFactory dslFactory) {

        dslFactory.pipelineJob(this.name) {
            it.description this.description
            logRotator(daysToKeep, numToKeep)
            concurrentBuild(concurrentBuilds)
            disabled(this.disabled)

            environmentVariablesWrapper(environmentVariablesFile, environmentVariables,
                    environmentVariablesScriptContent, environmentVariablesGroovyScript)

        }
    }

}
