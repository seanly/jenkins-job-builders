package xyz.opstack.jenkinsjobbuilders.domain.configure

class AbsPipelineFromJenkinsStep implements Configure {

    private final String pipelineName
    private String ciYmlTextVal
    private String argPropsTextVal

    private AbsPipelineFromJenkinsStep(String pipelineName) {
        this.pipelineName = pipelineName
    }

    AbsPipelineFromJenkinsStep absPipelineFromJenkinsStep(String pipelineName) {
        new AbsPipelineFromJenkinsStep(pipelineName)
    }

    AbsPipelineFromJenkinsStep withConfigure(String ciYmlTextVal, String argPropsTextVal) {
        this.ciYmlTextVal = ciYmlTextVal
        this.argPropsTextVal = argPropsTextVal
        this
    }

    @Override
    Closure toDsl() {
        return {
            it / 'builders' << 'xyz.opstack.jenkins.plugins.absplugin.AbsConfigBuilder' {
                'configSource'('class': 'xyz.opstack.jenkins.plugins.absplugin.config.ConfigFromJenkins') {
                    'ciYmlContent'(ciYmlTextVal)
                }
                'runPipeline'(pipelineName)
                'argProperties'(argPropsTextVal)
            }
        }
    }
}
