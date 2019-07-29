package build.dream.push.services;

import build.dream.common.api.ApiRest;
import build.dream.common.saas.domains.SocketClient;
import build.dream.common.utils.*;
import build.dream.push.constants.Constants;
import build.dream.push.models.account.SetAccountModel;
import build.dream.push.models.account.SetAliasModel;
import build.dream.push.models.account.SetTagModel;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.util.List;
import java.util.Objects;

@Service
public class SocketClientService {
    @Transactional(rollbackFor = Exception.class)
    public void saveSocketClient(String sessionId) {
        SearchModel searchModel = SearchModel.builder()
                .autoSetDeletedFalse()
                .addSearchCondition(SocketClient.ColumnName.SESSION_ID, Constants.SQL_OPERATION_SYMBOL_EQUAL, sessionId)
                .build();

        SocketClient socketClient = DatabaseHelper.find(SocketClient.class, searchModel);
        if (Objects.isNull(socketClient)) {
            socketClient = SocketClient.builder()
                    .sessionId(sessionId)
                    .account(Constants.VARCHAR_DEFAULT_VALUE)
                    .alias(Constants.VARCHAR_DEFAULT_VALUE)
                    .tag(Constants.VARCHAR_DEFAULT_VALUE)
                    .createdUserId(BigInteger.ZERO)
                    .updatedUserId(BigInteger.ZERO)
                    .build();
            DatabaseHelper.insert(socketClient);
        }
    }

    @Transactional(rollbackFor = Exception.class)
    public void deleteSocketClient(String sessionId) {
        UpdateModel updateModel = UpdateModel.builder()
                .autoSetDeletedFalse()
                .addSearchCondition(SocketClient.ColumnName.SESSION_ID, Constants.SQL_OPERATION_SYMBOL_EQUAL, sessionId)
                .addContentValue(SocketClient.ColumnName.DELETED, 1, 1)
                .build();
        DatabaseHelper.universalUpdate(updateModel, "socket_client");
    }

    @Transactional(readOnly = true)
    public List<SocketClient> obtainAllSocketClients(String target, String targetValue) {
        SearchModel searchModel = new SearchModel(true);
        if ("ACCOUNT".equals(target)) {
            searchModel.addSearchCondition(SocketClient.ColumnName.ACCOUNT, Constants.SQL_OPERATION_SYMBOL_EQUAL, targetValue);
        }

        if ("ALIAS".equals(target)) {
            searchModel.addSearchCondition(SocketClient.ColumnName.ACCOUNT, Constants.SQL_OPERATION_SYMBOL_EQUAL, targetValue);
        }

        if ("TAG".equals(target)) {
            searchModel.addSearchCondition(SocketClient.ColumnName.ACCOUNT, Constants.SQL_OPERATION_SYMBOL_EQUAL, targetValue);
        }

        return DatabaseHelper.findAll(SocketClient.class, searchModel);
    }

    @Transactional(rollbackFor = Exception.class)
    public ApiRest setAccount(SetAccountModel setAccountModel) {
        String account = setAccountModel.getAccount();
        String sessionId = ApplicationHandler.getSessionId();

        SearchModel searchModel = SearchModel.builder()
                .autoSetDeletedFalse()
                .addSearchCondition(SocketClient.ColumnName.SESSION_ID, Constants.SQL_OPERATION_SYMBOL_EQUAL, sessionId)
                .build();

        SocketClient socketClient = DatabaseHelper.find(SocketClient.class, searchModel);
        ValidateUtils.notNull(socketClient, "客户端不存在！");

        socketClient.setAccount(account);
        DatabaseHelper.update(socketClient);
        return ApiRest.builder().message("设置账号成功！").successful(true).build();
    }

    @Transactional(rollbackFor = Exception.class)
    public ApiRest setAlias(SetAliasModel setAliasModel) {
        String alias = setAliasModel.getAlias();
        String sessionId = ApplicationHandler.getSessionId();

        SearchModel searchModel = SearchModel.builder()
                .autoSetDeletedFalse()
                .addSearchCondition(SocketClient.ColumnName.SESSION_ID, Constants.SQL_OPERATION_SYMBOL_EQUAL, sessionId)
                .build();

        SocketClient socketClient = DatabaseHelper.find(SocketClient.class, searchModel);
        ValidateUtils.notNull(socketClient, "客户端不存在！");

        socketClient.setAlias(alias);
        DatabaseHelper.update(socketClient);
        return ApiRest.builder().message("设置别名成功！").successful(true).build();
    }

    @Transactional(rollbackFor = Exception.class)
    public ApiRest setTag(SetTagModel setTagModel) {
        String tag = setTagModel.getTag();
        String sessionId = ApplicationHandler.getSessionId();

        SearchModel searchModel = SearchModel.builder()
                .autoSetDeletedFalse()
                .addSearchCondition(SocketClient.ColumnName.SESSION_ID, Constants.SQL_OPERATION_SYMBOL_EQUAL, sessionId)
                .build();

        SocketClient socketClient = DatabaseHelper.find(SocketClient.class, searchModel);
        ValidateUtils.notNull(socketClient, "客户端不存在！");

        socketClient.setTag(tag);
        DatabaseHelper.update(socketClient);

        return ApiRest.builder().message("设置标签成功！").successful(true).build();
    }
}
