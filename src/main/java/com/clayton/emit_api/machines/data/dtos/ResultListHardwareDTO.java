package com.clayton.emit_api.machines.data.dtos;

import java.util.List;

import com.clayton.emit_api.machines.domain.interfaces.HardwareFiltersBase;

/**
 * DTO feito para padrõnização dos retornos de hardwares.
 * @author Clayton Charles
 * @version 0.1.0
 */
public record ResultListHardwareDTO(
    List<?> listHardware,
    HardwareFiltersBase filters,
    Long totalPages
) {
}
