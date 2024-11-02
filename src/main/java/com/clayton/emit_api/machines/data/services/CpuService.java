package com.clayton.emit_api.machines.data.services;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clayton.emit_api.auth.domain.entities.UserEntity;
import com.clayton.emit_api.machines.data.dtos.ResultListHardwareDTO;
import com.clayton.emit_api.machines.data.dtos.SaveCpuDTO;
import com.clayton.emit_api.machines.data.filters.CpuFiltersDTO;
import com.clayton.emit_api.machines.domain.entities.CpuEntity;
import com.clayton.emit_api.machines.domain.projections.CpuProjection;
import com.clayton.emit_api.machines.domain.repositories.CpuRepository;

@Service
public class CpuService {
    
    @Autowired
    private CpuRepository cpuRepository;

    /**
     * Resgata todas as CPUs cadastradas no sistema, de acordo com seus filtros.
     * @param filters {@link CpuFiltersDTO}
     * @return {@link ResultListHardwareDTO}
     * @author Clayton Charles
     * @version 1.0.0
     */
    public ResultListHardwareDTO getListCpu(CpuFiltersDTO filters) {
        Integer startRow = (filters.getPage() - 1) * filters.getItensPerPage();
        double totalItems = cpuRepository.countTotalCpus(filters.isActive());
        double totalPages = Math.ceil(totalItems / (double)filters.getItensPerPage());
        List<CpuProjection> listCpuProjections = cpuRepository.findProjectedAll(
            startRow,
            filters.getItensPerPage(),
            filters.getModel(),
            filters.getBrand(),
            filters.isActive()
        );
        ResultListHardwareDTO listCpu = new ResultListHardwareDTO(
            listCpuProjections, 
            filters, 
            (long)totalPages,
            (long)totalItems
        );
        return listCpu;
    }

    /**
     * Resgata as informações de uma CPU.
     * @param cpuId {@link Long}
     * @return {@link CpuProjection}
     * @author Clayton Charles
     * @version 0.1.0
     */
    public CpuProjection getInfoCpu(Long cpuId) {
        return cpuRepository.findProjectedById(cpuId);
    }

    /**
     * Valida se a CPU a qual está sendo tratada, e para uma atualização ou novo registro no sistema.
     * @param cpuInfo {@link SaveCpuDTO}
     * @param user {@link UserEntity}
     * @return {@link CpuProjection}
     * @throws Exception
     * @author Clayton Charles
     * @version 1.0.0
     */
    public CpuProjection saveCpu(SaveCpuDTO cpuInfo, UserEntity user) throws Exception {
        if (cpuInfo.id() == null || this.cpuRepository.findById(cpuInfo.id()).isEmpty()) {
            if (this.cpuRepository.findByModelAndActiveTrue(cpuInfo.model()).isPresent()) {
                throw new Exception("Can't register, because Cpu already register!");
            }

            return this.registerCpu(cpuInfo, user);
        }

        return this.updateCpu(cpuInfo, user);
    }

    /**
     * Registra uma nova CPU no sistema.
     * @param cpuInfo {@link SaveCpuDTO}
     * @param user {@link UserEntity}
     * @return {@link CpuProjection}
     * @author Clayton Charles
     * @version 1.0.0
     */
    public CpuProjection registerCpu(SaveCpuDTO cpuInfo, UserEntity user) {
        CpuEntity cpu = new CpuEntity(
            user,
            cpuInfo.brand(),
            cpuInfo.model(),
            cpuInfo.socket(),
            cpuInfo.cores(), 
            cpuInfo.threads(),
            cpuInfo.base_clock_speed(),
            cpuInfo.max_clock_speed(),
            cpuInfo.cache_l1(),
            cpuInfo.cache_l2(),
            cpuInfo.cache_l3(),
            cpuInfo.tdp_wattage_base(),
            cpuInfo.tdp_wattage_max(),
            cpuInfo.integrated_graphics(),
            cpuInfo.integrated_graphics_model(), 
            cpuInfo.photo_url()
        );

        CpuEntity cpuSave = this.cpuRepository.save(cpu);
        return this.cpuRepository.findProjectedById(cpuSave.getId());
    }

    /**
     * Atualiza os dados de CPU desejada.
     * @param cpuInfo {@link SaveCpuDTO}
     * @param user {@link UserEntity}
     * @return {@link CpuProjection}
     * @throws Exception
     * @author Clayton Charles
     * @version 1.0.0
     */
    public CpuProjection updateCpu(SaveCpuDTO cpuInfo, UserEntity user) throws Exception {
        CpuEntity cpu = this.cpuRepository.findById(cpuInfo.id()).get();
        cpu.setUser(user);
        cpu.setBrand(cpuInfo.brand());
        cpu.setModel(cpuInfo.model());
        cpu.setSocket(cpuInfo.socket());
        cpu.setCores(cpuInfo.cores()); 
        cpu.setThreads(cpuInfo.threads());
        cpu.setBase_clock_speed(cpuInfo.base_clock_speed());
        cpu.setMax_clock_speed(cpuInfo.max_clock_speed());
        cpu.setCache_l1(cpuInfo.cache_l1());
        cpu.setCache_l2(cpuInfo.cache_l2());
        cpu.setCache_l3(cpuInfo.cache_l3());
        cpu.setTdp_wattage_base(cpuInfo.tdp_wattage_base());
        cpu.setTdp_wattage_max(cpuInfo.tdp_wattage_max());
        cpu.setIntegrated_graphics(cpuInfo.integrated_graphics());
        cpu.setIntegrated_graphics_model(cpuInfo.integrated_graphics_model()); 
        cpu.setPhoto_url(cpuInfo.photo_url());
        cpu.setRegister_date(LocalDateTime.now());
        this.cpuRepository.save(cpu);
        return this.cpuRepository.findProjectedById(cpu.getId());
    }

    /**
     * Faz uma exclusão logica do sistema, mantendo o registro do mesmo.
     * @param id {@link Long}
     * @param user {@link UserEntity}
     * @throws Exception
     * @author Clayton Charles
     * @version 1.0.0
     */
    public void deactivateCpu(Long id, UserEntity user) throws Exception {
        CpuEntity cpu = this.cpuRepository.findByIdAndActiveTrue(id);
        if (cpu == null) {
            throw new Exception("Can't deactivate cpu, same already is deactivate");
        }

        cpu.setActive(false);
        cpu.setUser(user);
        this.cpuRepository.save(cpu);
    }
}