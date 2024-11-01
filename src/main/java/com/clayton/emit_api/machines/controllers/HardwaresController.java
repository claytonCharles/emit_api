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
import com.clayton.emit_api.machines.data.services.CpuService;
import com.clayton.emit_api.machines.domain.projections.CpuProjection;

import jakarta.validation.Valid;

@RestController
@RequestMapping("hardware")
public class HardwaresController {
    
    @Autowired
    private CpuService cpuService;

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
}
