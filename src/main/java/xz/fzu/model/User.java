package xz.fzu.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.io.Serializable;

/**
 * @author Murphy
 * @date 2019/4/19 13:21
 */
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    private String userId;
    private String passwd;
    private String telephone;
    private String userName;
    private String headUrl;
    private String email;
    private long sex;
    private String school;
    private String specialty;
    private String occupation;
    private String presentCity;
    private String expectedCity;

    /* token 临时存这里吧 //TODO 注意以后把这个从实体类中删除*/
    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }


    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }


    public String getHeadUrl() {
        return headUrl;
    }

    public void setHeadUrl(String headUrl) {
        this.headUrl = headUrl;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public long getSex() {
        return sex;
    }

    public void setSex(long sex) {
        this.sex = sex;
    }


    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }


    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }


    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }


    public String getPresentCity() {
        return presentCity;
    }

    public void setPresentCity(String presentCity) {
        this.presentCity = presentCity;
    }


    public String getExpectedCity() {
        return expectedCity;
    }

    public void setExpectedCity(String expectedCity) {
        this.expectedCity = expectedCity;
    }

}


