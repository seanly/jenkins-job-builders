package xyz.opstack.jenkinsjobbuilders.domain.scm

import uk.gov.hmrc.jenkinsjobbuilders.domain.scm.Scm

class GitScm implements Scm {

    private final String repository
    private final String branch
    private final String refspec
    private final String credentials
    private final String name

    private GitScm(String repository, String branch, String refspec, String credentials, String name) {
        this.branch = branch
        this.repository = repository
        this.refspec = refspec
        this.credentials = credentials
        this.name = name
    }

    static GitScm gitScm(String repository, String branch, String refspec, String credentials, String name = null ) {
        new GitScm(repository, branch, refspec, credentials, name)
    }

    @Override
    Closure toDsl() {
        return {
            git {
                remote {
                    url(repository)
                    if(this.refspec != null) {
                        refspec(this.refspec)
                    }
                    if(this.credentials != null) {
                        credentials(this.credentials)
                    }
                    if(this.name != null) {
                        name(this.name)
                    }
                }

                branch(this.branch)
            }
        }
    }
}
