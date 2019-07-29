package build.dream.push.models.account;

import build.dream.common.models.BasicModel;

import javax.validation.constraints.NotNull;

public class SetAccountModel extends BasicModel {
    @NotNull
    private String account;

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }
}
