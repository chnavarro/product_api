package com.tizo.productapi.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

/**
 * @author cnavarro
 * @version 1.0
 * @since 2022-11-03
 */
@Entity
@Table(name = "[USER]")
@Getter
@Setter
@NoArgsConstructor
public class User {

    public enum UserRole {
        USER, ADMIN
    }

    public User(String userName, String userFullName, String userPassword, String userRole, Boolean userStatus) {
        this.userName = userName;
        this.userFullName = userFullName;
        this.userPassword = userPassword;
        this.userRole = UserRole.valueOf(userRole);
        this.userStatus = userStatus;
    }

    @Id
    @Column(name = "USER_NAME", nullable = false, length = 10)
    private String userName;

    @Column(name = "USER_FULL_NAME", nullable = false, length = 100)
    private String userFullName;

    @Column(name = "USER_PASSWORD", nullable = false, length = 512)
    private String userPassword;

    @Column(name = "USER_CREATE_DATE", insertable = false, updatable = false, nullable = false)
    @Temporal( value = TemporalType.TIMESTAMP )
    @ColumnDefault("CURRENT_TIMESTAMP")
    private Date userCreateDate;

    @Temporal( value = TemporalType.TIMESTAMP )
    @Column(name = "USER_LAST_LOGIN")
    private Date userLastLogin;

    @Column(name = "USER_ROLE", length = 5)
    @ColumnDefault("'USER'")
    @Enumerated(EnumType.STRING)
    private UserRole userRole;

    @Column(name = "USER_STATUS", length = 11, columnDefinition = "BIT")
    @ColumnDefault("1")
    private Boolean userStatus;
}
