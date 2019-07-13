package com.yb.util;

import cn.yiban.util.HTTPSimple;

import static com.yb.config.YbMsg.*;

/**
 * 需要校级权限
 *
 * @author Jue-PC
 */
public class YbUtil {
    private String token;

    public YbUtil(String token) {
        this.token = token;
    }

    public String verifyme() {
        String query = YIBAN_OPEN_URL + YIBAN_USER_VERIFY_ME + "?access_token=" + token;
        return HTTPSimple.GET(query);
    }
}
