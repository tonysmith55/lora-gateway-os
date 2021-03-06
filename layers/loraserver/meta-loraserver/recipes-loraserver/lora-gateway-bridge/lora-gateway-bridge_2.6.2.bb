DESCRIPTION = "LoRa Gateway Bridge"
HOMEPAGE = "https://www.loraserver.io/"
PRIORITY = "optional"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=5301050fd7cd58850085239d559297be"
SRC_URI = "https://artifacts.loraserver.io/downloads/lora-gateway-bridge/lora-gateway-bridge_${PV}_linux_armv5.tar.gz \
           file://lora-gateway-bridge.toml \
           file://lora-gateway-bridge.init \
           file://lora-gateway-bridge.monit \
"
SRC_URI[md5sum] = "e120e06a478f3fef4b37359bdf510e04"
SRC_URI[sha256sum] = "bf3165e635b19800532701a526f2dc7aa4bb66aa1d6b1db95ab9e89dd1b330fe"
PR = "r1"

inherit update-rc.d

INITSCRIPT_NAME = "lora-gateway-bridge"
INITSCRIPT_PARAMS = "defaults"

S = "${WORKDIR}"
BIN_DIR = "/opt/lora-gateway-bridge"
CONF_DIR = "${sysconfdir}/lora-gateway-bridge"

do_install() {
    install -d ${D}${BIN_DIR}
    install -m 0755 lora-gateway-bridge ${D}${BIN_DIR}/

    install -d ${D}${CONF_DIR}
    install -m 0640 ${WORKDIR}/lora-gateway-bridge.toml ${D}${CONF_DIR}/lora-gateway-bridge.toml

    install -d ${D}${sysconfdir}/init.d
    install -m 0755 ${WORKDIR}/lora-gateway-bridge.init ${D}${sysconfdir}/init.d/lora-gateway-bridge

    install -d ${D}${sysconfdir}/monit.d
    install -m 0644 ${WORKDIR}/lora-gateway-bridge.monit ${D}${sysconfdir}/monit.d/lora-gateway-bridge
}

FILES_${PN} += "${BIN_DIR}"
FILES_${PN}-dbg += "${BIN_DIR}/.debug"

CONFFILES_${PN} += "${CONF_DIR}/lora-gateway-bridge.toml"
