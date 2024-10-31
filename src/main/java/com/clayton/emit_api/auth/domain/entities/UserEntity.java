package com.clayton.emit_api.auth.domain.entities;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "tb_users")
@Entity(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class UserEntity implements UserDetails {
    @Id
    @GeneratedValue( strategy = GenerationType.UUID )
    private String id; 

    @Column(unique = true)
    private String mail;

    private String password;

    private Boolean password_temp;

    private String name;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime birthday;

    private String photo;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "role_id", referencedColumnName = "id")
    private UserRolesEntity role;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime register_date;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> listRoles = List.of(new SimpleGrantedAuthority("ROLE_" + this.role.getName()));
        return listRoles;
    }

    @Override
    public String getUsername() {
        return mail;
    }

    public UserEntity(String mail, String name, String password) {
        this.mail = mail;
        this.name = name;
        this.password = password;
        this.role = new UserRolesEntity((long) 3);
        this.register_date = LocalDateTime.now();
    }

    public UserEntity(String mail, String name, String password, Boolean password_temp, Long role) {
        this.mail = mail;
        this.name = name;
        this.password = password;
        this.password_temp = password_temp;
        this.role = new UserRolesEntity(role);
        this.register_date = LocalDateTime.now();
    }
}
