package com.clayton.emit_api.machines.domain.projections;

import java.time.LocalDateTime;

/**
 * Projection para o tratar dados da CPU, evitando exposição de dados sensíveis.
 * @author Clayton Charles
 * @version 1.0.0
 */
public interface CpuProjection {
    Long getid();
    String getbrand();
    String getmodel();
    String getsocket();
    Integer getcores();
    Integer getthreads();
    double getbase_clock_speed();
    double getmax_clock_speed();
    String getcache_l1();
    String getcache_l2();
    String getcache_l3();
    Integer gettdp_wattage_base();
    Integer gettdp_wattage_max();
    Boolean getintegrated_graphics();
    String getintegrated_graphics_model();
    String getphoto_url();
    Boolean getactive();
    String getuser_id();
    String getuser_name();
    String getrole_user();
    LocalDateTime getregister_date();
}