package build.dream.push.models.account;

import build.dream.common.models.BasicModel;

import javax.validation.constraints.NotNull;

public class SetTagModel extends BasicModel {
    @NotNull
    private String tag;

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }
}
