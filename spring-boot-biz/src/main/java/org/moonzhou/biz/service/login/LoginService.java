package org.moonzhou.biz.service.login;

import lombok.extern.slf4j.Slf4j;
import org.moonzhou.biz.param.LoginParam;

/**
 * @author moonzhou
 */
@Slf4j
public abstract class LoginService {

    /**
     * 是否为此种登录方式
     * @param loginParam 登录方式
     * @return 返回是否为此登录方式
     */
    protected abstract boolean isThisLogin(LoginParam loginParam);

    /**
     * 认证登录，根据不同的登录方式，进行不同的数据验证
     * @param loginParam 登录参数
     * @return 返回登录结果
     */
    protected abstract boolean checkLoginInfo(LoginParam loginParam);

    /**
     * 登录事前校验，阻断，影响登录业务
     * @param loginParam 登录参数
     * @return true 有风险
     */
    boolean preLoginRiskProbe(LoginParam loginParam) {

        // 事前的风控，通用的，在抽象类里面统一处理，阻断登录型
        log.info("test risk before login");

        return false;
    }

    /**
     * 登录事中校验，阻断，影响登录业务
     * @param loginParam 登录参数
     * @return true 有风险
     */
    boolean loginRiskProbe(LoginParam loginParam) {

        // 事中风控，通用型，在抽象类里面统一处理，阻断登录型
        // 如果参数组装需要根据不同的登录方式进行构造，则需要抽象参数组织方法，此处调用，在各个子类的实现里进行分别处理

        log.info("test risk while login");

        return false;
    }

    /**
     * 登录事后通知，非阻断，不影响登录业务
     * 将登录结果信息，同步给风控相关系统，可异步操作
     * @param loginResult 登录结果
     * @param loginParam 登录参数
     */
    void notifyAfterLogin(String loginResult, LoginParam loginParam) {

        log.info("async notify login result to risk system");
    }

    /**
     * 登录
     * -1 登录失败，事前风控拦截
     * -2 登录失败，事中风控拦截
     * 1 验证通过，登录成功
     * 0 验证失败，登录失败
     * @param loginParam 登录参数
     * @return 登录结果
     */
    public String login(LoginParam loginParam) {
        String loginResult = "-1";

        // 事前风控
        if (preLoginRiskProbe(loginParam)) {
            return loginResult;
        }

        if (isThisLogin(loginParam)) {
            // 事中风控
            if (loginRiskProbe(loginParam)) {
                return "-2";
            }
            loginResult = checkLoginInfo(loginParam) ? "1" : "0";
        }

        // 登录之后进行事后通知
        notifyAfterLogin(loginResult, loginParam);

        return loginResult;
    }
}
