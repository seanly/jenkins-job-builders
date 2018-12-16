package uk.gov.hmrc.jenkinsjobbuilders.domain.wrapper

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
