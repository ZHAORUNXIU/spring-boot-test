package com.x.test.api.model.domain.user;

import com.x.test.common.annotation.CreatedAt;
import com.x.test.common.annotation.UpdatedAt;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.io.Serializable;
import java.util.Objects;

/**
 * 用户
 *
 * @author runxiu.zhao
 * @date 2023-06-07 18:00:00
 */
@Document(collection = "user")
public class UserMongoDO implements Serializable {

    private static final long serialVersionUID = 8718060005385905866L;

    /**
     * 主键
     */
    @MongoId
    private String id;

    /**
     * userId@
     */
    @Indexed
    private Long userId;

    private String phone;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 性别0未知1男2女
     */
    private Integer gender;

    /**
     * 生日
     */
    private Integer birth;

    @CreatedAt
    private Long createdAt;

    /**
     * 修改时间
     */
    @UpdatedAt
    private Long updatedAt;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

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

    public Long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Long createdAt) {
        this.createdAt = createdAt;
    }

    public Long getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Long updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserMongoDO that = (UserMongoDO) o;
        return Objects.equals(id, that.id) && Objects.equals(userId, that.userId) && Objects.equals(phone, that.phone) && Objects.equals(email, that.email) && Objects.equals(gender, that.gender) && Objects.equals(birth, that.birth) && Objects.equals(createdAt, that.createdAt) && Objects.equals(updatedAt, that.updatedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, phone, email, gender, birth, createdAt, updatedAt);
    }
}
