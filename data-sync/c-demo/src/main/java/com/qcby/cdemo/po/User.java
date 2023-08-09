package com.qcby.cdemo.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Objects;

@TableName(value = "user")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private String username;

    private String birthday;

    private Integer age;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }


    @Override
    public boolean equals(Object obj) {
        User other = (User) obj;
        if (this == obj) {
            return true;
        }
        if (this.id.equals(other.id)
                && this.username.equals(other.username)
                && this.birthday.equals(other.birthday)
                && this.age.equals(other.age)) {

            return true;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, birthday, age);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username=" + username +
                ", birthday=" + birthday +
                ", age=" + age +
                "}";
    }
}