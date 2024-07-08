package br.com.health.manager.domain.entities.user.dtos;

import br.com.health.manager.domain.entities.user.UserEntity;

import java.time.LocalDate;

public record UsuarioRetornoDto(
        Long id,
        String nome,
        String email,
        LocalDate dataDeNascimento,
        String telefone,
        String logradouro,
        String complemento,
        String bairro,
        String localidade,
        String uf,
        String ibge,
        String gia,
        String ddd,
        String siafi
) {
    public UsuarioRetornoDto(UserEntity user){
        this(
                user.getId(),
                user.getNome(),
                user.getEmail(),
                user.getDataDeNascimento(),
                user.getTelefone(),
                user.getLogradouro(),
                user.getComplemento(),
                user.getBairro(),
                user.getLocalidade(),
                user.getUf(),
                user.getIbge(),
                user.getGia(),
                user.getDdd(),
                user.getSiafi()
        );
    }
}
