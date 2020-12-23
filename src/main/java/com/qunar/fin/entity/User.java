package com.qunar.fin.entity;

import com.google.gson.annotations.SerializedName;

/**
 * @author guotao.gou
 * @version 1.0
 * @date 2019/9/24 10:47
 */
public class User {
    private String name;
    private Integer age;
//    @SerializedName("email_address")
    @SerializedName(value = "email_address", alternate = {"email", "emailAddress"})
    private String emailAddress;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", emailAddress='" + emailAddress + '\'' +
                '}';
    }
}
