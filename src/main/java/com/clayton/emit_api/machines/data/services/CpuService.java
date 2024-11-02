package com.clayton.emit_api.machines.data.services;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clayton.emit_api.auth.domain.entities.UserEntity;
import com.clayton.emit_api.machines.data.dtos.FiltersCpuDTO;
import com.clayton.emit_api.machines.data.dtos.ResultListHardwareDTO;
import com.clayton.emit_api.machines.data.dtos.SaveCpuDTO;
import com.clayton.emit_api.machines.domain.entities.CpuEntity;
import com.clayton.emit_api.machines.domain.projections.CpuProjection;
import com.clayton.emit_api.machines.domain.repositories.CpuRepository;

@Service
public class CpuService {
    
    @Autowired
    private CpuRepository cpuRepository;

    /**
     * Resgata todas as CPUs cadastradas no sistema, de acordo com seus filtros.
     * @param filters {@link FiltersCpuDTO}
     * @return {@link ResultListHardwareDTO}
     * @author Clayton Charles
     * @version 0.2.0
     */
    public ResultListHardwareDTO getListCpu(FiltersCpuDTO filters) {
        Integer startRow = (filters.getPage() - 1) * filters.getItensPerPage();
        double totalItems = cpuRepository.countTotalCpus(filters.isActive());
        double totalPages = Math.ceil(totalItems / (double)filters.getItensPerPage());
        List<CpuProjection> listCpuProjections = cpuRepository.findProjectedAll(
            startRow,
            filters.getItensPerPage(),
            filters.getName(),
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
     * @version 0.1.0
     */
    public CpuProjection saveCpu(SaveCpuDTO cpuInfo, UserEntity user) throws Exception {
        if (cpuInfo.id() == null || this.cpuRepository.findById(cpuInfo.id()).isEmpty()) {
            if (this.cpuRepository.findByNameAndActiveTrue(cpuInfo.name()).isPresent()) {
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
     * @version 0.1.0
     */
    public CpuProjection registerCpu(SaveCpuDTO cpuInfo, UserEntity user) {
        CpuEntity cpu = new CpuEntity(
            user,
            cpuInfo.brand(),
            cpuInfo.name(),
            cpuInfo.performance_core(),
            cpuInfo.efficient_core(),
            cpuInfo.total_core(),
            cpuInfo.total_threads(),
            cpuInfo.frequency_performance_base(),
            cpuInfo.frequency_performance_max(),
            cpuInfo.frequency_efficient_base(),
            cpuInfo.frequency_efficient_max(),
            cpuInfo.cache(),
            cpuInfo.cache_l2(),
            cpuInfo.psu_base(),
            cpuInfo.psu_max(),
            cpuInfo.photo()
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
     * @version 0.1.0
     */
    public CpuProjection updateCpu(SaveCpuDTO cpuInfo, UserEntity user) throws Exception {
        CpuEntity cpu = this.cpuRepository.findById(cpuInfo.id()).get();
        cpu.setUser_id(user);
        cpu.setBrand(cpuInfo.brand());
        cpu.setName(cpuInfo.name());
        cpu.setPerformance_core(cpuInfo.performance_core());
        cpu.setEfficient_core(cpuInfo.efficient_core());
        cpu.setTotal_core(cpuInfo.total_core());
        cpu.setTotal_threads(cpuInfo.total_threads());
        cpu.setFrequency_performance_base(cpuInfo.frequency_performance_base());
        cpu.setFrequency_performance_max(cpuInfo.frequency_performance_max());
        cpu.setFrequency_efficient_base(cpuInfo.frequency_efficient_base());
        cpu.setFrequency_efficient_max(cpuInfo.frequency_efficient_max());
        cpu.setCache(cpuInfo.cache());
        cpu.setCache_l2(cpuInfo.cache_l2());
        cpu.setPsu_base(cpuInfo.psu_base());
        cpu.setPsu_max(cpuInfo.psu_max());
        cpu.setPhoto(cpuInfo.photo());
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
     * @version 0.1.0
     */
    public void deactivateCpu(Long id, UserEntity user) throws Exception {
        CpuEntity cpu = this.cpuRepository.findByIdAndActiveTrue(id);
        if (cpu == null) {
            throw new Exception("Can't deactivate cpu, same already is deactivate");
        }

        cpu.setActive(false);
        cpu.setUser_id(user);
        this.cpuRepository.save(cpu);
    }
}