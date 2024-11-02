package com.clayton.emit_api.machines.domain.projections;

import java.time.LocalDateTime;

/**
 * Projection para o tratar dados da placa-mãe, evitando exposição de dados sensíveis.
 * @author Clayton Charles
 * @version 0.1.0
 */
public interface MotherboardProjection {
    Long getid();
    String getbrand();
    String getmodel();
    String getsocket_cpu();
    String getchipset();
    Integer getmemory_slots();
    String getmemory_type();
    Integer getmax_memory_capacity();
    Integer getpci_express_slots(); 
    Integer getsata_ports();
    Integer getm2_slots();
    Integer getusb_ports();
    String getusb_types();
    String getaudio_codecs();
    String getlan_ethernet();
    String getbios_uefi();
    String getform_factor();
    Boolean getpower_connector_24pin();
    Boolean getpower_connector_8pin();
    Integer getfan_connectors();
    Boolean getrgb_support();
    Boolean getactive();
    LocalDateTime getregister_date();
    String getuser_name();
    String getuser_id();
}