package com.clayton.emit_api.machines.domain.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.clayton.emit_api.machines.domain.entities.MotherboardEntity;
import com.clayton.emit_api.machines.domain.projections.MotherboardProjection;

public interface MotherboardRepository extends JpaRepository<MotherboardEntity, Long> {
    
    /**
     * Query simples para verificar se existe uma placa-mãe com o nome desejado ativo no sistema.
     * @param model {@link String}
     * @return {@link MotherboardEntity}
     * @author Clayton Charles
     * @version 0.1.0
     */
    Optional<MotherboardEntity> findByModelAndActiveTrue(String model);

    /**
     * Query simples para verificar se existe uma placa-mãe com o id desejado ativo no sistema.
     * @param id {@link Long}
     * @return {@link MotherboardEntity}
     * @author Clayton Charles
     * @version 0.1.0
     */
    MotherboardEntity findByIdAndActiveTrue(Long id);

    /**
     * Contabiliza todas as placas-mãe salvas no sistema.
     * @param itensPerPage {@link Double}
     * @param active {@link Boolean}
     * @return {@link Double}
     * @author Clayton Charles
     * @version 0.1.0
     */
    @Query(value = "SELECT COUNT(*) FROM tb_motherboard m WHERE m.active = :active", nativeQuery = true)
    double countTotalMotherboards(@Param("active") boolean active);

    /**
     * Resgata todas as placas-mãe do sistema, conforme os filtros desejados, assim como limitando as informações, 
     * para trazer apenas oque deve/pode ser exibido.
     * @param startRow
     * @param itensPerPage
     * @param model
     * @param brand
     * @param active
     * @return Lista de {@link MotherboardProjection}
     * @author Clayton Charles
     * @version 0.1.0
     */
    @Query(
        value = "SELECT " +
                    "m.*, " +
                    "u.id AS user_id, " + 
                    "u.name AS user_name, " + 
                    "r.name as role_user " +
                "FROM tb_motherboard m " +
                    "JOIN tb_users u ON m.user_id = u.id " +
                    "JOIN tb_users_roles r ON u.role_id = r.id " +
                "WHERE m.active = :active " +
                "AND (m.model LIKE %:model% OR :model IS NULL) " +
                "AND (m.brand LIKE %:brand% OR :brand IS NULL) " +
                "LIMIT :startRow, :itensPerPage",
        nativeQuery = true
    )
    List<MotherboardProjection> findProjectedAll(
        @Param("startRow") Integer startRow, 
        @Param("itensPerPage")Integer itensPerPage,
        @Param("model") String model,
        @Param("brand") String brand,
        @Param("active") boolean active
    );

    /**
     * Resgata todas as informações que podem ser visualizada pelo usuário sobre a placa-mãe desejada.
     * @param id {@link Long}
     * @return {@link MotherboardProjection}
     * @author Clayton Charles
     * @version 0.1.0
     */
    @Query(
        value = "SELECT " +
                    "m.*, " +
                    "u.id AS user_id, " + 
                    "u.name AS user_name, " + 
                    "r.name as role_user " +
                "FROM tb_motherboard m " +
                    "JOIN tb_users u ON m.user_id = u.id " +
                    "JOIN tb_users_roles r ON u.role_id = r.id " +
                    "WHERE m.active = true AND m.id = ?1",
        nativeQuery = true
    )
    MotherboardProjection findProjectedById(Long id);
}