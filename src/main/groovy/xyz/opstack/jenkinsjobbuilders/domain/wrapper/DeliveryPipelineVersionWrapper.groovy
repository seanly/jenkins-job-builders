package xyz.opstack.jenkinsjobbuilders.domain.wrapper

import uk.gov.hmrc.jenkinsjobbuilders.domain.wrapper.Wrapper

class DeliveryPipelineVersionWrapper implements Wrapper {

    private final String template

    private DeliveryPipelineVersionWrapper(String template) {
        this.template = template
    }

    static deliveryPipelineVersionWrapper(String template) {
        new DeliveryPipelineVersionWrapper(template)
    }

    @Override
    Closure toDsl() {
        return {
            deliveryPipelineVersion(this.template, true)
        }
    }
}
