CREATE TABLE IF NOT EXISTS tb_motherboard (
    id INT AUTO_INCREMENT PRIMARY KEY,
    user_id VARCHAR(64) NOT NULL,
    brand VARCHAR(30) NOT NULL,
    model VARCHAR(50) NOT NULL,
    socket_cpu VARCHAR(50) NOT NULL,
    chipset VARCHAR(50),
    memory_slots INT NOT NULL,
    memory_type VARCHAR(50) NOT NULL,
    max_memory_capacity INT NOT NULL,
    pci_express_slots INT NOT NULL, 
    sata_ports INT NOT NULL,
    m2_slots INT NOT NULL,
    usb_ports INT, 
    usb_types VARCHAR(50),
    audio_codecs VARCHAR(50), 
    lan_ethernet VARCHAR(50), 
    bios_uefi VARCHAR(50), 
    form_factor VARCHAR(50),
    power_connector_24pin BOOLEAN NOT NULL, 
    power_connector_8pin BOOLEAN NOT NULL, 
    fan_connectors INT,
    rgb_support BOOLEAN,
    photo_url VARCHAR(255),
    active BOOLEAN,
    register_date DATETIME NOT NULL DEFAULT NOW(),
    FOREIGN KEY (user_id) REFERENCES tb_users(id)
);

CREATE TABLE IF NOT EXISTS rtb_motherboard (
    id INT AUTO_INCREMENT PRIMARY KEY,
    motherboard_id INT,
    user_id VARCHAR(64),
    brand VARCHAR(30),
    model VARCHAR(50),
    socket_cpu VARCHAR(50),
    chipset VARCHAR(50),
    memory_slots INT,
    memory_type VARCHAR(50),
    max_memory_capacity INT,
    pci_express_slots INT, 
    sata_ports INT,
    m2_slots INT,
    usb_ports INT, 
    usb_types VARCHAR(50),
    audio_codecs VARCHAR(50), 
    lan_ethernet VARCHAR(50), 
    bios_uefi VARCHAR(50), 
    form_factor VARCHAR(50),
    power_connector_24pin BOOLEAN, 
    power_connector_8pin BOOLEAN, 
    fan_connectors INT, 
    rgb_support BOOLEAN,
    photo_url VARCHAR(255),
    active BOOLEAN,
    register_date DATETIME,
    FOREIGN KEY (motherboard_id) REFERENCES tb_motherboard(id),
    FOREIGN KEY (user_id) REFERENCES tb_users(id)
);

CREATE TRIGGER IF NOT EXISTS record_tb_motherboard AFTER UPDATE ON tb_motherboard FOR EACH ROW INSERT INTO rtb_motherboard (
    motherboard_id,
    user_id,
    brand,
    model,
    socket_cpu,
    chipset,
    memory_slots,
    memory_type,
    max_memory_capacity,
    pci_express_slots, 
    sata_ports,
    m2_slots,
    usb_ports, 
    usb_types,
    audio_codecs, 
    lan_ethernet, 
    bios_uefi, 
    form_factor,
    power_connector_24pin, 
    power_connector_8pin, 
    fan_connectors, 
    rgb_support,
    photo_url,
    active,
    register_date
) VALUES (
    OLD.id,
    OLD.user_id,
    OLD.brand,
    OLD.model,
    OLD.socket_cpu,
    OLD.chipset,
    OLD.memory_slots,
    OLD.memory_type,
    OLD.max_memory_capacity,
    OLD.pci_express_slots, 
    OLD.sata_ports,
    OLD.m2_slots,
    OLD.usb_ports, 
    OLD.usb_types,
    OLD.audio_codecs, 
    OLD.lan_ethernet, 
    OLD.bios_uefi, 
    OLD.form_factor,
    OLD.power_connector_24pin, 
    OLD.power_connector_8pin, 
    OLD.fan_connectors, 
    OLD.rgb_support,
    OLD.photo_url,
    OLD.active,
    OLD.register_date
);