package uk.gov.hmrc.jenkinsjobbuilders.domain.trigger

import javaposse.jobdsl.dsl.helpers.triggers.GitLabTriggerContext

class GitLabTrigger implements Trigger {

    private String secretToken
    private String branchFilterType
    private String includeBranchesSpec
    private String excludeBranchesSpec

    private GitLabTrigger() {
    }

    static GitLabTrigger gitlabTrigger() {
        new GitLabTrigger()
    }

    GitLabTrigger withSecretToken(String secretToken) {
        this.secretToken = secretToken
        this
    }

    GitLabTrigger withBranchFilter(String branchFilterType, String includeBranchesSpec, String excludeBranchesSpec) {
        this.branchFilterType = branchFilterType
        this.includeBranchesSpec = includeBranchesSpec
        this.excludeBranchesSpec = excludeBranchesSpec
        this
    }

    @Override
    Closure toDsl() {
        gitlab {
            triggerOnAcceptedMergeRequest(true)
            triggerOnApprovedMergeRequest(false)
            triggerOnClosedMergeRequest(false)
            triggerOnMergeRequest(false)
            triggerOnNoteRequest(true)
            triggerOnPipelineEvent(false)
            triggerOnPush(true)
            triggerOpenMergeRequestOnPush('never')

            ciSkip(true)
            skipWorkInProgressMergeRequest(true)
            setBuildDescription(true)

            branchFilterType(this.branchFilterType)
            includeBranchesSpec(this.includeBranchesSpec)
            excludeBranchesSpec(this.excludeBranchesSpec)
            secretToken(this.secretToken)

            pendingBuildName('')
            cancelPendingBuildsOnUpdate(false)
            targetBranchRegex('')

        }
    }
}
