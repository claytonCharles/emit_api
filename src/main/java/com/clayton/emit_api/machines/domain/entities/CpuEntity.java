package com.clayton.emit_api.machines.domain.entities;

import java.time.LocalDateTime;

import com.clayton.emit_api.auth.domain.entities.UserEntity;
import com.fasterxml.jackson.annotation.JsonFormat;

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

/**
 * Entidade para identificação e tratamento de dados via JPA da tabela de CPUs do sistema.
 * @author Clayton Charles
 * @version 0.1.0
 */
@Table(name = "tb_cpus")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class CpuEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "register_user_id", referencedColumnName = "id")
    private UserEntity user_id;

    private String brand;
    private String name;
    private Integer performance_core;
    private Integer efficient_core;
    private Integer total_core;
    private Integer total_threads;
    private String frequency_performance_base;
    private String frequency_performance_max;
    private String frequency_efficient_base;
    private String frequency_efficient_max;
    private String cache;
    private String cache_l2;
    private String psu_base;
    private String psu_max;
    private String photo;
    private boolean active;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime register_date;

    public CpuEntity(String brand, String name) {
        this.brand = brand;
        this.name = name;
        this.active = true;
        this.register_date = LocalDateTime.now();
    }

    public CpuEntity(
        UserEntity user_id,
        String brand,
        String name,
        Integer performance_core,
        Integer efficient_core,
        Integer total_core,
        Integer total_threads,
        String frequency_performance_base,
        String frequency_performance_max,
        String frequency_efficient_base,
        String frequency_efficient_max,
        String cache,
        String cache_l2,
        String psu_base,
        String psu_max,
        String photo
    ) {
        this.user_id = user_id;
        this.brand = brand;
        this.name = name;
        this.performance_core = performance_core;
        this.efficient_core = efficient_core;
        this.total_core = total_core;
        this.total_threads = total_threads;
        this.frequency_performance_base = frequency_performance_base;
        this.frequency_performance_max = frequency_performance_max;
        this.frequency_efficient_base = frequency_efficient_base;
        this.frequency_efficient_max = frequency_efficient_max;
        this.cache = cache;
        this.cache_l2 = cache_l2;
        this.psu_base = psu_base;
        this.psu_max = psu_max;
        this.photo = photo;
        this.active = true;
        this.register_date = LocalDateTime.now();
    }

    public CpuEntity(
        Long id,
        UserEntity user_id,
        String brand,
        String name,
        Integer performance_core,
        Integer efficient_core,
        Integer total_core,
        Integer total_threads,
        String frequency_performance_base,
        String frequency_performance_max,
        String frequency_efficient_base,
        String frequency_efficient_max,
        String cache,
        String cache_l2,
        String psu_base,
        String psu_max,
        String photo
    ) {
        this.id = id;
        this.user_id = user_id;
        this.brand = brand;
        this.name = name;
        this.performance_core = performance_core;
        this.efficient_core = efficient_core;
        this.total_core = total_core;
        this.total_threads = total_threads;
        this.frequency_performance_base = frequency_performance_base;
        this.frequency_performance_max = frequency_performance_max;
        this.frequency_efficient_base = frequency_efficient_base;
        this.frequency_efficient_max = frequency_efficient_max;
        this.cache = cache;
        this.cache_l2 = cache_l2;
        this.psu_base = psu_base;
        this.psu_max = psu_max;
        this.photo = photo;
        this.active = true;
        this.register_date = LocalDateTime.now();
    }
}