package com.junyao.cxj.story.constant;

/**
 * Created by Administrator on 2017/1/18 0018.
 */

public class StoyInter {
    public static final String HOST = "http://139.129.19.51/story/";//根地址
    public static final String INTER_ROOT = HOST+ "index.php/home/Interface/";//接口根地址
    public static final String IMAGE_ROOT = HOST + "Uploads/";//图片根地址
    public static final String PORTRAIT_ROOT = HOST + "Uploads/portrait/";//头像根地址

    public static final String REGIST = INTER_ROOT+ "regist";//注册
    public static final String LOGIN = INTER_ROOT+ "login";//登录
    public static final String SENDSTORY = INTER_ROOT+ "sendStory";//发表故事
    public static final String GETSTORY = INTER_ROOT+ "getStorys";//获取故事
    public static final String READSTORYS = INTER_ROOT+ "readStorys";//阅读故事
    public static final String MYSTORYS = INTER_ROOT+ "myStorys";//我的故事
    public static final String CHANGENICKNAME = INTER_ROOT+ "changeNickName";//修改昵称
    public static final String CHANGESEX = INTER_ROOT+ "changeSex";//修改性别
    public static final String CHANGEEMAIL = INTER_ROOT+ "changeEmail";//修改邮箱
    public static final String CHANGEBIRTHDAY = INTER_ROOT+ "changeBirthday";//修改生日
    public static final String CHANGESIGNATURE = INTER_ROOT+ "changeSignature";//修改签名
    public static final String CHANGEPORTRAIT = INTER_ROOT+ "changePortrait";//修改头像
    public static final String CHANGEPASSWORD = INTER_ROOT+ "changePassword";//修改密码
    public static final String SENDCOMMENT = INTER_ROOT+ "sendComment";//发表评论
    public static final String GETCOMMENTS = INTER_ROOT+ "getComments";//获取评论
}
