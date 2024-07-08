package br.com.health.manager.domain.entities;

import br.com.health.manager.domain.entities.enums.Especialidade;
import br.com.health.manager.domain.entities.user.UserEntity;
import jakarta.persistence.*;

@Entity
@Table(name = "tb_doctors")
public class DoctorEntity {

    @Id
    private Long id;

    @MapsId
    @OneToOne
    @JoinColumn(name = "usuario_id")
    private UserEntity usuario;

    @Enumerated(EnumType.STRING)
    private Especialidade especialidade;

    @Column(unique = true, nullable = false)
    private String crm;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserEntity getUsuario() {
        return usuario;
    }

    public void setUsuario(UserEntity usuario) {
        this.usuario = usuario;
    }

    public Especialidade getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(Especialidade especialidade) {
        this.especialidade = especialidade;
    }

    public String getCrm() {
        return crm;
    }

    public void setCrm(String crm) {
        this.crm = crm;
    }
}
