package uk.gov.hmrc.jenkinsjobbuilders.domain.configure

class AbsReportPublisher implements Configure{

    private AbsReportPublisher() {

    }

    static AbsReportPublisher absReportPublisher() {
        new AbsReportPublisher()
    }

    @Override
    Closure toDsl() {
        return {
            it / 'publishers' << 'xyz.opstack.jenkins.plugins.absplugin.AbsReportPublisher' {}
        }
    }
}
