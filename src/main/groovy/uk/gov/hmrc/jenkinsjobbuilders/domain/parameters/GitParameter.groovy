package uk.gov.hmrc.jenkinsjobbuilders.domain.parameters

class GitParameter implements Parameter {

    private final String paramName
    private final String paramDescription
    private String paramType = "PT_TYPE"
    private final String paramDefaultValue = ""

    private GitParameter(String paramName, String paramDescription) {
        this.paramName = paramName
        this.paramDescription = paramDescription
    }

    static GitParameter scmGitParameter(String paramName, String paramDescription) {
        new GitParameter(paramName, paramDescription)
    }

    GitParameter withType(String paramType) {
        this.paramType = paramType
        this
    }

    @Override
    Closure toDsl() {
        return {
            gitParameter {
                name(paramName)
                type(paramType)
                defaultValue(paramDefaultValue)
                description(paramDecription)
                branchFilter(".*")
                sortMode("DESCENDING_SMART")
                selectedValue("TOP")
                quickFilterEnabled(true)
                listSize("5")
                branch('')
                tagFilter("*")
                useRepository('')
            }
        }
    }
}
