package xyz.opstack.jenkinsjobbuilders.domain.wrapper

import uk.gov.hmrc.jenkinsjobbuilders.domain.wrapper.Wrapper

class BuildNameWrapper implements Wrapper {

    private final String nameTemplate

    private BuildNameWrapper(String nameTemplate) {
        this.nameTemplate = nameTemplate
    }

    static BuildNameWrapper buildNameWrapper(String nameTemplate) {
        new BuildNameWrapper(nameTemplate)
    }

    @Override
    Closure toDsl() {
        return {
            buildName(this.nameTemplate)
        }
    }
}
