package com.clayton.emit_api.machines.domain.projections;

import java.time.LocalDateTime;

/**
 * Projection para o tratar dados da CPU, evitando exposição de dados sensíveis.
 * @author Clayton Charles
 * @version 0.1.0
 */
public interface CpuProjection {
    Long getid();
    String getbrand();
    String getname();
    Integer getperformance_core();
    Integer getefficient_core();
    Integer gettotal_core();
    Integer gettotal_threads();
    String getfrequency_performance_base();
    String getfrequency_performance_max();
    String getfrequency_efficient_base();
    String getfrequency_efficient_max();
    String getcache();
    String getcache_l2();
    String getpsu_base();
    String getpsu_max();
    String getphoto();
    boolean getactive();
    String getuser_id();
    String getuser_name();
    String getrole_user();
    LocalDateTime getregister_date();
}