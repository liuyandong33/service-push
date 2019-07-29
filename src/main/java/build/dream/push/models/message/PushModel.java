package build.dream.push.models.message;

import build.dream.common.models.BasicModel;

import javax.validation.constraints.NotNull;

public class PushModel extends BasicModel {
    @NotNull
    private String target;

    @NotNull
    private String targetValue;

    @NotNull
    private String body;

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public String getTargetValue() {
        return targetValue;
    }

    public void setTargetValue(String targetValue) {
        this.targetValue = targetValue;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
