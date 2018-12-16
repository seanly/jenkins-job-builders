package xyz.opstack.jenkinsjobbuilders.domain.wrapper

import uk.gov.hmrc.jenkinsjobbuilders.domain.wrapper.Wrapper

class InjectPasswordsWrapper implements Wrapper {

    private InjectPasswordsWrapper() {

    }

    static InjectPasswordsWrapper injectPasswordsWrapper() {
        new InjectPasswordsWrapper()
    }

    @Override
    Closure toDsl() {
        return {
            injectPasswords {
                injectGlobalPasswords()
                maskPasswordParameters()
            }
        }
    }
}
