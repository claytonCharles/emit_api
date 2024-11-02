package com.clayton.emit_api.machines.data.dtos;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
/**
 * DTO responsável para a validação e dos dados na requisição para cadastro/atualização de CPUs.
 * @author Clayton Charles
 * @version 1.0.0
 */
public record SaveCpuDTO(
    Long id,
    @NotEmpty(message = "Can't be empty!")
    String brand,
    @NotEmpty(message = "Can't be empty!")
    String model,
    @NotEmpty(message = "Can't be empty!")
    String socket,
    Integer cores, 
    Integer threads,
    double base_clock_speed,
    double max_clock_speed,
    String cache_l1,
    String cache_l2,
    String cache_l3,
    @NotNull(message = "Can't be empty!")
    Integer tdp_wattage_base,
    Integer tdp_wattage_max,
    @NotNull(message = "Can't be empty!")
    Boolean integrated_graphics,
    String integrated_graphics_model, 
    String photo_url
) {
}