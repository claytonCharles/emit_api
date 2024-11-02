package com.clayton.emit_api.machines.data.dtos;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

/**
 * DTO responsável para a validação e dos dados na requisição para cadastro/atualização de Motherboard.
 * @author Clayton Charles
 * @version 0.1.0
 */
public record SaveMotherboardDTO(
    Long id,
    @NotEmpty(message = "Can't be empty!")
    String brand,
    @NotEmpty(message = "Can't be empty!")
    String model,
    @NotEmpty(message = "Can't be empty!")
    String socket_cpu,
    String chipset,
    @NotNull(message = "Can't be empty!")
    Integer memory_slots,
    @NotEmpty(message = "Can't be empty!")
    String memory_type,
    @NotNull(message = "Can't be empty!")
    Integer max_memory_capacity,
    @NotNull(message = "Can't be empty!")
    Integer pci_express_slots, 
    @NotNull(message = "Can't be empty!")
    Integer sata_ports,
    @NotNull(message = "Can't be empty!")
    Integer m2_slots,
    Integer usb_ports,
    String usb_types,
    String audio_codecs,
    String lan_ethernet,
    String bios_uefi,
    String form_factor,
    @NotNull(message = "Can't be empty!")
    Boolean power_connector_24pin,
    @NotNull(message = "Can't be empty!")
    Boolean power_connector_8pin,
    Integer fan_connectors,
    Boolean rgb_support,
    String photo_url
) {
}