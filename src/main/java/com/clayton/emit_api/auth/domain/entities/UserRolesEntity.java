package com.clayton.emit_api.auth.domain.entities;

import java.time.LocalDateTime;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Entidade para identificação das permissões de usuários do sistema.
 * @author Clayton Charles
 * @version 0.1.1
 */
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

    public UserRolesEntity(Long id) {
        this.id = id;
    }
}
