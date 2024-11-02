package com.clayton.emit_api.machines.data.services;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clayton.emit_api.auth.domain.entities.UserEntity;
import com.clayton.emit_api.machines.data.dtos.ResultListHardwareDTO;
import com.clayton.emit_api.machines.data.dtos.SaveCpuDTO;
import com.clayton.emit_api.machines.data.dtos.SaveMotherboardDTO;
import com.clayton.emit_api.machines.data.filters.MotherboardFiltersDTO;
import com.clayton.emit_api.machines.domain.entities.MotherboardEntity;
import com.clayton.emit_api.machines.domain.projections.MotherboardProjection;
import com.clayton.emit_api.machines.domain.repositories.MotherboardRepository;

@Service
public class MotherboardService {

    @Autowired
    private MotherboardRepository motherboardRepository;

    /**
     * Resgata todas as placas-mãe cadastradas no sistema, de acordo com seus filtros.
     * @param filters {@link MotherboardFiltersDTO}
     * @return {@link ResultListHardwareDTO}
     * @author Clayton Charles
     * @version 0.1.0
     */
    public ResultListHardwareDTO recoveMotherboards(MotherboardFiltersDTO filters) {
        Integer startRow = (filters.getPage() - 1) * filters.getItensPerPage();
        double totalItems = motherboardRepository.countTotalMotherboards(filters.isActive());
        double totalPages = Math.ceil(totalItems / (double)filters.getItensPerPage());
        List<MotherboardProjection> listMotherboardsProject = motherboardRepository.findProjectedAll(
            startRow,
            filters.getItensPerPage(),
            filters.getModel(),
            filters.getBrand(),
            filters.isActive()
        );
        ResultListHardwareDTO listMotherboards = new ResultListHardwareDTO(
            listMotherboardsProject, 
            filters, 
            (long)totalPages,
            (long)totalItems
        );
        return listMotherboards;
    }

    /**
     * Resgata as informações de uma placa-mãe.
     * @param motherboardId {@link Long}
     * @return {@link MotherboardProjection}
     * @author Clayton Charles
     * @version 0.1.0
     */
    public MotherboardProjection recoveMotherboardInfo(Long motherboardId) {
        return motherboardRepository.findProjectedById(motherboardId);
    }

    /**
     * Valida se a placa-mãe a qual está sendo tratada, e para uma atualização ou novo registro no sistema.
     * @param motherboard {@link SaveMotherboardDTO}
     * @param user {@link UserEntity}
     * @return {@link MotherboardProjection}
     * @throws Exception
     * @author Clayton Charles
     * @version 0.1.0
     */
    public MotherboardProjection saveMotherboard(SaveMotherboardDTO motherboard, UserEntity user) throws Exception {
        if (motherboard.id() == null || this.motherboardRepository.findById(motherboard.id()).isEmpty()) {
            if (this.motherboardRepository.findByModelAndActiveTrue(motherboard.model()).isPresent()) {
                throw new Exception("Can't register, because Motherboard already register!");
            }

            return this.registerMotherboard(motherboard, user);
        }

        return this.updateMotherboard(motherboard, user);
    }


    /**
     * Registra uma nova placa-mãe no sistema.
     * @param cpuInfo {@link SaveMotherboardDTO}
     * @param user {@link UserEntity}
     * @return {@link MotherboardProjection}
     * @author Clayton Charles
     * @version 0.1.0
     */
    public MotherboardProjection registerMotherboard(SaveMotherboardDTO motherboard, UserEntity user) {
        MotherboardEntity infosMotherboard = new MotherboardEntity(
            user,
            motherboard.brand(),
            motherboard.model(),
            motherboard.socket_cpu(),
            motherboard.chipset(),
            motherboard.memory_slots(),
            motherboard.memory_type(),
            motherboard.max_memory_capacity(),
            motherboard.pci_express_slots(), 
            motherboard.sata_ports(),
            motherboard.m2_slots(),
            motherboard.usb_ports(), 
            motherboard.usb_types(),
            motherboard.audio_codecs(), 
            motherboard.lan_ethernet(), 
            motherboard.bios_uefi(), 
            motherboard.form_factor(),
            motherboard.power_connector_24pin(),
            motherboard.power_connector_8pin(),
            motherboard.fan_connectors(),
            motherboard.rgb_support(),
            motherboard.photo_url()
        );

        MotherboardEntity motherboardSaved = this.motherboardRepository.save(infosMotherboard);
        return this.motherboardRepository.findProjectedById(motherboardSaved.getId());
    }

    /**
     * Atualiza os dados de uma placa-mãe desejada.
     * @param cpuInfo {@link SaveCpuDTO}
     * @param user {@link UserEntity}
     * @return {@link MotherboardProjection}
     * @throws Exception
     * @author Clayton Charles
     * @version 0.1.0
     */
    public MotherboardProjection updateMotherboard(SaveMotherboardDTO motherboard, UserEntity user) throws Exception {
        MotherboardEntity infosMotherboard = this.motherboardRepository.findById(motherboard.id()).get();
        infosMotherboard.setUser(user);
        infosMotherboard.setBrand(motherboard.brand());
        infosMotherboard.setModel(motherboard.model());
        infosMotherboard.setSocket_cpu(motherboard.socket_cpu());
        infosMotherboard.setChipset(motherboard.chipset());
        infosMotherboard.setMemory_slots(motherboard.memory_slots());
        infosMotherboard.setMemory_type(motherboard.memory_type());
        infosMotherboard.setMax_memory_capacity(motherboard.max_memory_capacity());
        infosMotherboard.setPci_express_slots(motherboard.pci_express_slots()); 
        infosMotherboard.setSata_ports(motherboard.sata_ports());
        infosMotherboard.setM2_slots(motherboard.m2_slots());
        infosMotherboard.setUsb_ports(motherboard.usb_ports()); 
        infosMotherboard.setUsb_types(motherboard.usb_types());
        infosMotherboard.setAudio_codecs(motherboard.audio_codecs()); 
        infosMotherboard.setLan_ethernet(motherboard.lan_ethernet()); 
        infosMotherboard.setBios_uefi(motherboard.bios_uefi()); 
        infosMotherboard.setForm_factor(motherboard.form_factor());
        infosMotherboard.setPower_connector_24pin(motherboard.power_connector_24pin());
        infosMotherboard.setPower_connector_8pin(motherboard.power_connector_8pin());
        infosMotherboard.setFan_connectors(motherboard.fan_connectors());
        infosMotherboard.setRgb_support(motherboard.rgb_support());
        infosMotherboard.setRegister_date(LocalDateTime.now());
        this.motherboardRepository.save(infosMotherboard);
        return this.motherboardRepository.findProjectedById(infosMotherboard.getId());
    }

    /**
     * Faz uma exclusão logica do sistema, mantendo o registro do mesmo.
     * @param id {@link Long}
     * @param user {@link UserEntity}
     * @throws Exception
     * @author Clayton Charles
     * @version 0.1.0
     */
    public void deactivateMotherboard(Long id, UserEntity user) throws Exception {
        MotherboardEntity motherboard = this.motherboardRepository.findByIdAndActiveTrue(id);
        if (motherboard == null) {
            throw new Exception("Can't deactivate motherboard, same already is deactivate or not exist");
        }

        motherboard.setActive(false);
        motherboard.setUser(user);
        this.motherboardRepository.save(motherboard);
    }
}