package com.guomiaomiao.pojo;

public class User {
    private Integer id;

    private String name;

    private String password;

    private Integer sex;

    private Byte isVip;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public Byte isVip() {
        return isVip;
    }

    public void setVip(Byte vip) {
        isVip = vip;
    }

    public User(String name, String password, Integer sex, Byte isVip) {
        this.name = name;
        this.password = password;
        this.sex = sex;
        this.isVip = isVip;
    }
    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", sex=" + sex +
                ", isVip=" + isVip +
                '}';
    }
}