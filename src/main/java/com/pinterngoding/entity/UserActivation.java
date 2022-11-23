package com.pinterngoding.entity;

import com.pinterngoding.shared.classes.BaseEntity;
import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "user_activations")
public class UserActivation extends BaseEntity {
    @Column(name = "activation_code", nullable = false)
    private String activationCode;
    @Column(name = "expire_date", columnDefinition = "timestamp NOT NULL DEFAULT (CURRENT_TIMESTAMP + '2 hours'::interval)")
    @Temporal(TemporalType.TIMESTAMP)
    private Date expireDate;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)
    private User relatedUser;

    public String getActivationCode() {
        return activationCode;
    }

    public void setActivationCode(String activationCode) {
        this.activationCode = activationCode;
    }

    public Date getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(Date expireDate) {
        this.expireDate = expireDate;
    }

    public User getRelatedUser() {
        return relatedUser;
    }

    public void setRelatedUser(User relatedUser) {
        this.relatedUser = relatedUser;
    }
}