package com.junyao.cxj.story.bean;

/**
 * Created by Administrator on 2017/1/20 0020.
 */

public class RegistBean {

    /**
     * result : 1
     * msg : 恭喜！注册成功
     * data : {"username":"pig","userpass":"MTRlMWI2MDBiMWZkNTc5ZjQ3NDMzYjg4ZThkODUyOTE=","nickname":"小🐷爱洗澡"}
     */

    private int result;
    private String msg;
    private DataBean data;

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * username : pig
         * userpass : MTRlMWI2MDBiMWZkNTc5ZjQ3NDMzYjg4ZThkODUyOTE=
         * nickname : 小🐷爱洗澡
         */

        private String username;
        private String userpass;
        private String nickname;

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getUserpass() {
            return userpass;
        }

        public void setUserpass(String userpass) {
            this.userpass = userpass;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }
    }
}
