package xyz.opstack.jenkinsjobbuilders.domain.configure

import uk.gov.hmrc.jenkinsjobbuilders.domain.configure.Configure

class AbsTaskStep implements Configure{

    private final String taskId
    private Map<String, String> properties

    private AbsTaskStep(String taskId) {
        this.taskId = taskId
    }

    static AbsTaskStep absTaskStep(String taskId) {
        new AbsTaskStep(taskId)
    }

    AbsTaskStep withProperties(Map<String, String> properties) {
        this.properties = properties
        this
    }

    @Override
    Closure toDsl() {
        return {
            it / 'builders' << 'xyz.opstack.jenkins.plugins.absplugin.AbsTaskBuilder' {
                'taskId'(this.taskId)
                'taskVars' {
                    this.properties.each { k, v ->
                        'taskVars' << 'xyz.opstack.jenkins.plugins.absplugin.TaskVar' {
                            key k
                            value v
                            hidden false
                        }
                    }
                }
            }
        }
    }
}
