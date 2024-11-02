package com.clayton.emit_api.machines.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.clayton.emit_api.auth.domain.entities.UserEntity;
import com.clayton.emit_api.core.data.dtos.SimpleResponseDTO;
import com.clayton.emit_api.machines.data.dtos.FiltersCpuDTO;
import com.clayton.emit_api.machines.data.dtos.ResultListHardwareDTO;
import com.clayton.emit_api.machines.data.dtos.SaveCpuDTO;
import com.clayton.emit_api.machines.data.dtos.SaveMotherboardDTO;
import com.clayton.emit_api.machines.data.filters.MotherboardFiltersDTO;
import com.clayton.emit_api.machines.data.services.CpuService;
import com.clayton.emit_api.machines.data.services.MotherboardService;
import com.clayton.emit_api.machines.domain.projections.CpuProjection;
import com.clayton.emit_api.machines.domain.projections.MotherboardProjection;

import jakarta.validation.Valid;

@RestController
@RequestMapping("hardware")
public class HardwaresController {
    
    @Autowired
    private CpuService cpuService;

    @Autowired
    private MotherboardService motherboardService;

    @GetMapping("list/cpu")
    public ResponseEntity<ResultListHardwareDTO> getListCpu(@RequestBody @Valid FiltersCpuDTO filters) {
        ResultListHardwareDTO lstCpu = this.cpuService.getListCpu(filters);
        return ResponseEntity.ok(lstCpu);
    }

    @GetMapping("info/cpu")
    public ResponseEntity<CpuProjection> getCpuInfo(@RequestParam Long id) {
        CpuProjection cpuProjection = this.cpuService.getInfoCpu(id);
        return ResponseEntity.ok(cpuProjection);
    }   

    @PostMapping("save/cpu")
    public ResponseEntity<CpuProjection> saveCpu(@RequestBody @Valid SaveCpuDTO cpuDTO) throws Exception {
        UserEntity user = (UserEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        CpuProjection cpuEntity = this.cpuService.saveCpu(cpuDTO, user);
        return ResponseEntity.ok(cpuEntity);
    }

    @GetMapping("deactive/cpu")
    public ResponseEntity<SimpleResponseDTO> removeCpu(@RequestParam Long id) throws Exception {
        UserEntity user = (UserEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        this.cpuService.deactivateCpu(id, user);
        return ResponseEntity.ok(new SimpleResponseDTO("Cpu deactivate with success!", true));
    }

    @GetMapping("list/motherboard")
    public ResponseEntity<ResultListHardwareDTO> getAllMotherboards(
        @RequestBody @Valid MotherboardFiltersDTO filters
    ) {
        ResultListHardwareDTO listMotherboard = this.motherboardService.recoveMotherboards(filters);
        return ResponseEntity.ok(listMotherboard);
    }

    @GetMapping("info/motherboard")
    public ResponseEntity<MotherboardProjection> recoveMotherboardInfo(@RequestParam Long id) {
        MotherboardProjection motherboard = this.motherboardService.recoveMotherboardInfo(id);
        return ResponseEntity.ok(motherboard);
    } 

    @PostMapping("save/motherboard")
    public ResponseEntity<MotherboardProjection> saveMotherboard(
        @RequestBody @Valid SaveMotherboardDTO motherboardDTO
    ) throws Exception {
        UserEntity user = (UserEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        MotherboardProjection motherboard = this.motherboardService.saveMotherboard(motherboardDTO, user);
        return ResponseEntity.ok(motherboard);
    }

    @GetMapping("deactive/motherboard")
    public ResponseEntity<SimpleResponseDTO> deactivateMotherboard(@RequestParam Long id) throws Exception {
        UserEntity user = (UserEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        this.motherboardService.deactivateMotherboard(id, user);
        return ResponseEntity.ok(new SimpleResponseDTO("Motherboard deactivate with success!", true));
    }
}
