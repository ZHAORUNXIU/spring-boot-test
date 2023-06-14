package com.x.test.gateway.model.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * mongo test: Add to MongoDB request param
 *
 * @author runxiu.zhao
 * @date 2023-06-07 18:44:00
 */
@ApiModel(value = "User information")
public class AddUserReq {

    @NotNull
    @Length(min = 9, max = 12)
    @ApiModelProperty(value = "phone")
    private String phone;

    @ApiModelProperty(value = "email")
    private String email;

    /**
     * 性别 0未知 1男 2女
     */
    @Min(0)
    @Max(2)
    @ApiModelProperty(value = "gender", allowableValues = "0, 1, 2")
    private Integer gender;

    @ApiModelProperty(value = "birth")
    private Integer birth;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public Integer getBirth() {
        return birth;
    }

    public void setBirth(Integer birth) {
        this.birth = birth;
    }
}
