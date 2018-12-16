package xyz.opstack.jenkinsjobbuilders.domain.builder

import javaposse.jobdsl.dsl.DslFactory
import javaposse.jobdsl.dsl.Job

class PipelineDefinitionScmBuilder extends PipelineBuilder{

    private String gitRepoUrl
    private String gitCredId
    private String gitBranch
    private String jenkinsfilePath
    private boolean lightweightCheckout

    PipelineDefinitionScmBuilder(String name, String description) {
        super(name, description)
    }

    PipelineBuilder withGitScm(String gitRepoUrl, String gitCredId, String gitBranch, String jenkinsfilePath, lightweightCheckout) {
        this.gitRepoUrl = gitRepoUrl
        this.gitCredId = gitCredId
        this.gitBranch = gitBranch
        this.jenkinsfilePath = jenkinsfilePath
        this.lightweightCheckout = lightweightCheckout
        this
    }

    @Override
    Job build(DslFactory dslFactory) {

        job = super.build(dslFactory)

        job.definition {
            cpsScm {
                scm {
                    git {
                        remote {
                            url(gitRepoUrl)
                            if (gitCredId != null) {
                                credentials(gitCredId)
                            }
                        }

                        branch(gitBranch)
                        scriptPath(jenkinsfilePath)
                        lightweight(lightweightCheckout)
                    }
                }
            }
        }
    }
}
