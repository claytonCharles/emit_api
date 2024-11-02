package com.clayton.emit_api.machines.domain.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.clayton.emit_api.machines.domain.entities.CpuEntity;
import com.clayton.emit_api.machines.domain.projections.CpuProjection;

public interface CpuRepository extends JpaRepository<CpuEntity, Long> {

    /**
     * Query simples para verificar se existe uma CPU com o nome desejado ativo no sistema.
     * @param name {@link String}
     * @return {@link CpuEntity}
     * @author Clayton Charles
     * @version 0.1.0
     */
    Optional<CpuEntity> findByNameAndActiveTrue(String name);

    /**
     * Query simples para verificar se existe uma CPU com o id desejado ativo no sistema.
     * @param id {@link Long}
     * @return {@link CpuEntity}
     * @author Clayton Charles
     * @version 0.1.0
     */
    CpuEntity findByIdAndActiveTrue(Long id);

    /**
     * Contabiliza todas as CPUs salvas no sistema.
     * @param itensPerPage {@link Double}
     * @param active {@link Boolean}
     * @return {@link Double}
     * @author Clayton Charles
     * @version 0.2.0
     */
    @Query(value = "SELECT COUNT(*) FROM tb_cpus c WHERE c.active = :active", nativeQuery = true)
    double countTotalCpus(@Param("active") boolean active);

    /**
     * Resgata todas as CPUs do sistema, conforme os filtros desejados, assim como limitando as informações, 
     * para trazer apenas oque deve/pode ser exibido.
     * @param startRow
     * @param itensPerPage
     * @param cpuName
     * @param brand
     * @param active
     * @return Lista de {@link CpuProjection}
     * @author Clayton Charles
     * @version 0.1.0
     */
    @Query(
        value = "SELECT " +
                    "c.*, " +
                    "u.id AS user_id, " + 
                    "u.name AS user_name, " + 
                    "r.name as role_user " +
                "FROM tb_cpus c " +
                    "JOIN tb_users u ON c.register_user_id = u.id " +
                    "JOIN tb_users_roles r ON u.role_id = r.id " +
                "WHERE c.active = :active " +
                "AND (c.name LIKE %:cpuName% OR :cpuName IS NULL) " +
                "AND (c.brand LIKE %:brand% OR :brand IS NULL) " +
                "LIMIT :startRow, :itensPerPage",
        nativeQuery = true
    )
    List<CpuProjection> findProjectedAll(
        @Param("startRow") Integer startRow, 
        @Param("itensPerPage")Integer itensPerPage,
        @Param("cpuName") String cpuName,
        @Param("brand") String brand,
        @Param("active") boolean active
    );

    /**
     * Resgata todas as informações que podem ser visualizada pelo usuário sobre a CPU desejada.
     * @param id {@link Long}
     * @return {@link CpuProjection}
     * @author Clayton Charles
     * @version 0.1.0
     */
    @Query(
        value = "SELECT " +
                    "c.*, " +
                    "u.id AS user_id, " + 
                    "u.name AS user_name, " + 
                    "r.name as role_user " +
                "FROM tb_cpus c " +
                    "JOIN tb_users u ON c.register_user_id = u.id " +
                    "JOIN tb_users_roles r ON u.role_id = r.id " +
                    "WHERE c.active = true AND c.id = ?1",
        nativeQuery = true
    )
    CpuProjection findProjectedById(Long id);
}