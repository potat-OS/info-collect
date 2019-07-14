package com.yb.util;

import cn.yiban.open.Authorize;
import cn.yiban.util.HTTPSimple;

import static com.yb.config.YbMsg.*;

/**
 * 需要校级权限
 *
 * @author Jue-PC
 */
public class YbUtil {
    private String token;

    public YbUtil(String accessToken) {
        token = accessToken;
    }

    public String verifyMe() {
        String query = YIBAN_OPEN_URL
                + YIBAN_USER_VERIFY_ME
                + "?access_token="
                + token;
        return HTTPSimple.GET(query);
    }

    public Authorize.Man getUtil() {
        Authorize authorize = new Authorize(APP_ID, APP_SEC);
        return authorize.getManInstance(token);
    }
}
