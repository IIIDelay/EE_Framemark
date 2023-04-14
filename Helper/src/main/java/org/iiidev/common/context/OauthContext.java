package org.iiidev.common.context;

public class OauthContext {
    private static final ThreadLocal<LoginVal> loginValThreadLocal = new ThreadLocal<>();

    /**
     * get
     *
     * @return LoginVal
     */
    public static LoginVal get() {
        return loginValThreadLocal.get();
    }

    /**
     * set
     *
     * @param loginVal loginVal
     */
    public static void set(LoginVal loginVal) {
        loginValThreadLocal.set(loginVal);
    }

    /**
     * clear
     */
    public static void clear() {
        loginValThreadLocal.remove();
    }
}