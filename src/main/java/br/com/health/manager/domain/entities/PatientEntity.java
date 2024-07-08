package br.com.health.manager.domain.entities;

import br.com.health.manager.domain.entities.user.UserEntity;
import jakarta.persistence.*;

@Entity
@Table(name = "tb_patients")
public class PatientEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @MapsId
    @OneToOne
    @JoinColumn(name = "usuario_id")
    private UserEntity usuario;

    @Column(name = "numero_plano_saude", unique = true)
    private String numeroPlanoSaude;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserEntity getUsuario() {
        return usuario;
    }

    public void setUsuario(UserEntity user) {
        this.usuario = user;
    }

    public String getNumeroPlanoSaude() {
        return numeroPlanoSaude;
    }

    public void setNumeroPlanoSaude(String healthInsuranceNumber) {
        this.numeroPlanoSaude = healthInsuranceNumber;
    }
}
