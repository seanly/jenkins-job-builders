package uk.gov.hmrc.jenkinsjobbuilders.domain.wrapper

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
