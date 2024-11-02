package com.clayton.emit_api.machines.data.filters;

import com.clayton.emit_api.machines.domain.interfaces.HardwareFiltersBase;

import lombok.Getter;
import lombok.Setter;

/**
 * Implementação dos filtros para a CPU.
 * @author Clayton Charles
 * @version 1.0.0
 */
@Getter
@Setter
public class CpuFiltersDTO implements HardwareFiltersBase {
    private Long id;
    private String model;
    private String brand;
    private boolean active = true;
    private Integer page;
    private Integer itensPerPage = 10;
}