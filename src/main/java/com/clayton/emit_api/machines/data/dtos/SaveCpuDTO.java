package com.clayton.emit_api.machines.data.dtos;

import jakarta.validation.constraints.NotEmpty;
/**
 * DTO responsável para a validação e dos dados na requisição para cadastro/atualização de CPUs.
 * @author Clayton Charles
 * @version 0.1.0
 */
public record SaveCpuDTO(
    Long id,
    @NotEmpty(message = "Brand can't be empty!")
    String brand,
    @NotEmpty(message = "Name can't be empty!")
    String name,
    int performance_core,
    int efficient_core,
    int total_core,
    int total_threads,
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
}