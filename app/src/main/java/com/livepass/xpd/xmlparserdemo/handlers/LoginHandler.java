package com.livepass.xpd.xmlparserdemo.handlers;

import android.util.Log;

import com.livepass.xpd.xmlparserdemo.models.User;

/**
 * Created by VuVT on 11/11/2015.
 * default
 */
public class LoginHandler extends BaseHandler {

    final String USER = "User"; //begin get content from xml at tag <User>
    private User user = null;

    @Override
    protected void processNotEndErrorCode(String localName) {
        if (localName.equalsIgnoreCase("id")) {
            user.setId(builder.toString().trim());
        } else if (localName.equalsIgnoreCase("user_name")) {
            user.setUserName(builder.toString().trim());
        } else if (localName.equalsIgnoreCase("email")) {
            user.setEmail(builder.toString().trim());
        }

    }

    @Override
    protected Object returnNotErrorCode() {
        if (user != null) {
            return user;
        }
        return null;
    }

    @Override
    public void processNotStartErrorCode(String localName) {
        if (localName.equalsIgnoreCase(USER))
            user = new User();
    }
}
