package example.recipes.db.model;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.persistence.*;
import java.math.BigInteger;
import java.time.ZonedDateTime;


@Getter
@Setter
@Entity
@Table(name = "sd_01_001_device_certificates")
public class DeviceCertificateEntity {

    public static final String SD_SEQUENCE_GENERATOR = "sd_sequence_gen";


    @Id
    @SequenceGenerator(
            sequenceName = "sd_common_sequence",
            name = SD_SEQUENCE_GENERATOR)
    @GeneratedValue(generator = SD_SEQUENCE_GENERATOR,
            strategy = GenerationType.SEQUENCE)
    @Column(name = "certificate_id", nullable = false)
    private BigInteger certificateId;
    @Column(name = "device_uid", nullable = false)
    private String deviceUid;
    @Column(name = "device_certificate", nullable = false)
    private byte[] deviceCertificate;
    @Column(name = "device_certificate_fingerprint", nullable = false)
    private String deviceCertificateFingerprint;
    @Column(name = "device_certificate_valid_from", nullable = false)
    private ZonedDateTime deviceCertificateValidFrom;
    @Column(name = "device_certificate_valid_till", nullable = false)
    private ZonedDateTime deviceCertificateValidTill;
    @Column(name = "is_revoked_device_certificate")
    private boolean isRevokedDeviceCertificate;
    @Column(name = "creation_date")
    private ZonedDateTime creationDate;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        DeviceCertificateEntity that = (DeviceCertificateEntity) o;

        return new EqualsBuilder()
                .append(certificateId, that.certificateId)
                .append(deviceUid, that.deviceUid)
                .append(deviceCertificateFingerprint, that.deviceCertificateFingerprint)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(certificateId)
                .append(deviceUid)
                .append(deviceCertificateFingerprint)
                .toHashCode();
    }
}
