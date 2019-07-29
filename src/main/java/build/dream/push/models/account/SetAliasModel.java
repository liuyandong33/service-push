package build.dream.push.models.account;

import build.dream.common.models.BasicModel;

import javax.validation.constraints.NotNull;

public class SetAliasModel extends BasicModel {
    @NotNull
    private String alias;

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }
}
