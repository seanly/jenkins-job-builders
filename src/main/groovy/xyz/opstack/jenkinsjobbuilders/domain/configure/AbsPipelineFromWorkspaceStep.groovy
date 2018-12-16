package xyz.opstack.jenkinsjobbuilders.domain.configure

import uk.gov.hmrc.jenkinsjobbuilders.domain.configure.Configure

class AbsPipelineFromWorkspaceStep implements Configure{

    private final String pipelineName
    private String ciYmlPathVal
    private String argPropsTextVal

    private AbsPipelineFromWorkspaceStep(String pipelineName) {
        this.pipelineName = pipelineName
    }

    static AbsPipelineFromWorkspaceStep absPipelineFromWorkspaceStep(String pipelineName) {
        new AbsPipelineFromWorkspaceStep(pipelineName)
    }

    AbsPipelineFromWorkspaceStep withConfigure(String ciYmlPathVal, String argPropsTextVal) {
        this.ciYmlPathVal = ciYmlPathVal
        this.argPropsTextVal = argPropsTextVal
        this
    }

    @Override
    Closure toDsl() {
        return {
           it / 'builders' << 'xyz.opstack.jenkins.plugins.absplugin.AbsConfigBuilder' {
                'configSource'('class': 'xyz.opstack.jenkins.plugins.absplugin.config.ConfigFromWorkspace') {
                    'ciYmlPath'(ciYmlPathVal)
                }
                'runPipeline'(pipelineName)
                'argProperties'(argPropsTextVal)
            }
        }
    }
}
