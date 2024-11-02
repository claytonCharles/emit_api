package com.clayton.emit_api.machines.data.filters;

import com.clayton.emit_api.machines.domain.interfaces.HardwareFiltersBase;

import lombok.Getter;
import lombok.Setter;

/**
 * Implementação dos filtros para as consultas de placas-mãe.
 * @author Clayton Charles
 * @version 0.2.0
 */
@Getter
@Setter
public class MotherboardFiltersDTO implements HardwareFiltersBase {
    private Long id;
    private String brand;
    private String model;
    private boolean active = true;
    private Integer page;
    private Integer itensPerPage = 10;
}