package com.mirandasidney.pdv.api.domain;

import com.mirandasidney.pdv.api.util.Util;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "USER")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final Boolean STATUS = true;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_ID")
    @Getter
    private Long userId;

    @Getter
    @Setter
    @Column(name = "FIRSTNAME", nullable = false)
    private String firstname;

    @Getter
    @Setter
    @Column(name = "LASTNAME", nullable = false)
    private String lastname;

    @Getter
    @Setter
    @Column(name = "USER_NAME", nullable = false)
    private String username;

    @Getter
    @Setter
    @Column(name = "PASSWORD", nullable = false)
    private String password;

    @Getter
    @Setter
    @Column(name = "PHONE")
    private String phone;

    @Getter
    @Setter
    @ManyToOne
    @JoinColumn(name = "PROFILE_ID", nullable = false)
    private Profile profile;

    @Getter
    @Setter
    @Column(name = "CREATED_AT")
    private String createdAt = Util.formatDate();

    @Getter
    @Setter
    @Column(name = "STATUS")
    private Boolean status = STATUS;

}
