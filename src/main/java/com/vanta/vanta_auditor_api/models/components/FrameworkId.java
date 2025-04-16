/* 
 * Code generated by Speakeasy (https://speakeasy.com). DO NOT EDIT.
 */
package com.vanta.vanta_auditor_api.models.components;

import com.fasterxml.jackson.annotation.JsonValue;
import java.lang.String;
import java.util.Objects;
import java.util.Optional;

public enum FrameworkId {
    AU_E8("AU_E_8"),
    AWS_FTR("AWS_FTR"),
    CCPA("CCPA"),
    CIS_V8("CIS_V8"),
    CPS234("CPS_234"),
    DORA("DORA"),
    FEDRAMP("FEDRAMP"),
    GDPR("GDPR"),
    HIPAA("HIPAA"),
    HITRUST_E1("HITRUST_E1"),
    ISO27001("ISO_27001"),
    ISO270012022("ISO_27001_2022"),
    ISO27017("ISO_27017"),
    ISO27018("ISO_27018"),
    ISO27701("ISO_27701"),
    ISO42001("ISO_42001"),
    ISO9001("ISO_9001"),
    MSFT_SSPA("MSFT_SSPA"),
    MVSP("MVSP"),
    NIS2_D("NIS_2D"),
    NIST171("NIST_171"),
    NIST53("NIST_53"),
    NIST_AI_RMF("NIST_AI_RMF"),
    NIST_CSF("NIST_CSF"),
    NIST_CSF2("NIST_CSF_2"),
    OFDSS("OFDSS"),
    PCI_SAQ_A("PCI_SAQ_A"),
    PCI_SAQ_A_EP("PCI_SAQ_A_EP"),
    PCI_SAQ_D_MERCHANT("PCI_SAQ_D_MERCHANT"),
    PCI_SAQ_D_SP("PCI_SAQ_D_SP"),
    PCI_DDS4("PCI_DDS_4"),
    SOC2("SOC_2"),
    SOX_ITGC("SOX_ITGC"),
    UK_CYBER_ESSENTIALS("UK_CYBER_ESSENTIALS"),
    US_DATA_PRIVACY("US_DATA_PRIVACY");

    @JsonValue
    private final String value;

    private FrameworkId(String value) {
        this.value = value;
    }
    
    public String value() {
        return value;
    }
    
    public static Optional<FrameworkId> fromValue(String value) {
        for (FrameworkId o: FrameworkId.values()) {
            if (Objects.deepEquals(o.value, value)) {
                return Optional.of(o);
            }
        }
        return Optional.empty();
    }
}

