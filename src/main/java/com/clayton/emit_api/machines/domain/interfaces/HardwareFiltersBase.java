package com.clayton.emit_api.machines.domain.interfaces;

import jakarta.validation.constraints.NotNull;

/**
 * Interface para implementações de filtros dos hardwares, servindo como modelo base e obrigatório.
 * @author Clayton Charles
 * @version 1.0.0
 */
public interface HardwareFiltersBase {
    Long getId();
    String getModel();
    String getBrand();
    boolean isActive();
    @NotNull(message = "page can't be empty")
    Integer getPage();
    Integer getItensPerPage();
}