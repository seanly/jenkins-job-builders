package xyz.opstack.jenkinsjobbuilders.domain.configure

import uk.gov.hmrc.jenkinsjobbuilders.domain.configure.Configure

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
