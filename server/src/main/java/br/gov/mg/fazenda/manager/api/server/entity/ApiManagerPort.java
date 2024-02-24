package br.gov.mg.fazenda.manager.api.server.entity;

import br.gov.mg.fazenda.manager.api.server.enumeration.AmbienteEnum;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import static jakarta.persistence.EnumType.STRING;
import static jakarta.persistence.FetchType.LAZY;
import static jakarta.persistence.GenerationType.IDENTITY;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "api_manager_port")
public class ApiManagerPort {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    @Column(name = "porta", nullable = false)
    private Integer porta;

    @Column(name = "link_swagger")
    private String linkSwagger;

    @Column(name = "secret_db_status")
    private String secretDbStatus;

    @Column(name = "host_name")
    private String hostName;

    @Enumerated(STRING)
    @Column(name = "ambiente")
    private AmbienteEnum ambiente;

    @ManyToOne(fetch = LAZY, optional = false)
    @JoinColumn(name = "api_manager_id", referencedColumnName = "id")
    private ApiManager apiManager;

}
