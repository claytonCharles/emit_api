package com.clayton.emit_api.auth.domain.entities;

import java.time.LocalDateTime;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "tb_users_roles")
@Entity(name = "users_roles")
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Getter
@Setter
public class UserRolesEntity {
    @Id
    private Long id;
    private String name;
    private LocalDateTime register_date;

    @OneToOne(mappedBy = "role")
    private UserEntity user;

    public UserRolesEntity(Long id) {
        this.id = id;
    }
}
