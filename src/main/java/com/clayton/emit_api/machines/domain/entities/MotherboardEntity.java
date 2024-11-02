package com.clayton.emit_api.machines.domain.entities;

import java.time.LocalDateTime;

import com.clayton.emit_api.auth.domain.entities.UserEntity;
import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "tb_motherboard")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class MotherboardEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private UserEntity user;

    private String brand;
    private String model;
    private String socket_cpu;
    private String chipset;
    private Integer memory_slots;
    private String memory_type;
    private Integer max_memory_capacity;
    private Integer pci_express_slots; 
    private Integer sata_ports;
    private Integer m2_slots;
    private Integer usb_ports; 
    private String usb_types;
    private String audio_codecs; 
    private String lan_ethernet; 
    private String bios_uefi; 
    private String form_factor;
    private Boolean power_connector_24pin;
    private Boolean power_connector_8pin;
    private Integer fan_connectors;
    private Boolean rgb_support;
    private String photo_url;
    private Boolean active;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime register_date;

    public MotherboardEntity(
        UserEntity user,
        String brand,
        String model,
        String socket_cpu,
        String chipset,
        Integer memory_slots,
        String memory_type,
        Integer max_memory_capacity,
        Integer pci_express_slots, 
        Integer sata_ports,
        Integer m2_slots,
        Integer usb_ports, 
        String usb_types,
        String audio_codecs, 
        String lan_ethernet, 
        String bios_uefi, 
        String form_factor,
        Boolean power_connector_24pin,
        Boolean power_connector_8pin,
        Integer fan_connectors,
        Boolean rgb_support,
        String photo_url
    ) {
        this.user = user;
        this.brand = brand;
        this.model = model;
        this.socket_cpu = socket_cpu;
        this.chipset = chipset;
        this.memory_slots = memory_slots;
        this.memory_type = memory_type;
        this.max_memory_capacity = max_memory_capacity;
        this.pci_express_slots = pci_express_slots;
        this.sata_ports = sata_ports;
        this.m2_slots = m2_slots;
        this.usb_ports = usb_ports;
        this.usb_types = usb_types;
        this.audio_codecs = audio_codecs;
        this.lan_ethernet = lan_ethernet;
        this.bios_uefi = bios_uefi;
        this.form_factor = form_factor;
        this.power_connector_24pin = power_connector_24pin;
        this.power_connector_8pin = power_connector_8pin;
        this.fan_connectors = fan_connectors;
        this.rgb_support = rgb_support;
        this.photo_url = photo_url;
        this.active = true;
        this.register_date = LocalDateTime.now();
    }
}