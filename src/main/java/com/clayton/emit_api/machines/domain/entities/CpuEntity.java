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
 * @version 1.0.0
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
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private UserEntity user;

    private String brand;
    private String model;
    private String socket;
    private Integer cores; 
    private Integer threads;
    private double base_clock_speed;
    private double max_clock_speed;
    private String cache_l1;
    private String cache_l2;
    private String cache_l3;
    private Integer tdp_wattage_base;
    private Integer tdp_wattage_max;
    private Boolean integrated_graphics;
    private String integrated_graphics_model; 
    private String photo_url;
    private Boolean active;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime register_date;

    public CpuEntity(
        UserEntity user,
        String brand,
        String model,
        String socket,
        Integer cores, 
        Integer threads,
        double base_clock_speed,
        double max_clock_speed,
        String cache_l1,
        String cache_l2,
        String cache_l3,
        Integer tdp_wattage_base,
        Integer tdp_wattage_max,
        Boolean integrated_graphics,
        String integrated_graphics_model, 
        String photo_url
    ) {
        this.user = user;
        this.brand = brand;
        this.model = model;
        this.socket = socket;
        this.cores = cores;
        this.threads = threads;
        this.base_clock_speed = base_clock_speed;
        this.max_clock_speed = max_clock_speed;
        this.cache_l1 = cache_l1;
        this.cache_l2 = cache_l2;
        this.cache_l3 = cache_l3;
        this.tdp_wattage_base = tdp_wattage_base;
        this.tdp_wattage_max = tdp_wattage_max;
        this.integrated_graphics = integrated_graphics;
        this.integrated_graphics_model = integrated_graphics_model;
        this.photo_url = photo_url;
        this.active = true;
        this.register_date = LocalDateTime.now();
    }
}