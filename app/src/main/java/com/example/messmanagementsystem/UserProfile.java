package com.example.messmanagementsystem;

public class UserProfile {
    private String UserName;
    private String UserErno;
    private String UserMobno;
    private String UserEmail;
    private String UserPassword;
    private String UserGender;

    public UserProfile(String name, String erno, String mobno)
    {
        UserName = name;
        UserErno = erno;
        UserMobno = mobno;
    }

    public UserProfile()
    {
    }


    public UserProfile(String userName, String userErno, String userMobno, String userEmail, String userPassword, String userGender) {
        UserName = userName;
        UserErno = userErno;
        UserMobno = userMobno;
        UserEmail = userEmail;
        UserPassword = userPassword;
        UserGender = userGender;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getUserErno() {
        return UserErno;
    }

    public void setUserErno(String userErno) {
        UserErno = userErno;
    }

    public String getUserMobno() {
        return UserMobno;
    }

    public void setUserMobno(String userMobno) {
        UserMobno = userMobno;
    }

    public String getUserEmail() {
        return UserEmail;
    }

    public void setUserEmail(String userEmail) {
        UserEmail = userEmail;
    }

    public String getUserPassword() {
        return UserPassword;
    }

    public void setUserPassword(String userPassword) {
        UserPassword = userPassword;
    }

    public String getUserGender() {
        return UserGender;
    }

    public void setUserGender(String userGender) {
        UserGender = userGender;
    }
}