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
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "USER")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final Boolean STATUS = true;

    @Id
    @Getter
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "USER_ID", updatable = false, unique = true, nullable = false, columnDefinition = "uuid")
    private UUID uuid;

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
