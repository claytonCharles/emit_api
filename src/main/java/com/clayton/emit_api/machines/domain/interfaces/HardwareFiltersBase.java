package com.clayton.emit_api.machines.domain.interfaces;

import jakarta.validation.constraints.NotNull;

/**
 * Interface para implementações de filtros dos hardwares, servindo como modelo base e obrigatório.
 * @author Clayton Charles
 * @version 0.1.0
 */
public interface HardwareFiltersBase {
    Long getId();
    String getName();
    String getBrand();
    boolean isActive();
    @NotNull(message = "page can't be empty")
    Integer getPage();
    Integer getItensPerPage();
}