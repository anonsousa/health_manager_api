package br.com.health.manager.domain.entities.user.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.time.LocalDate;

public record UsuarioAddDto(

        @NotBlank
        String nome,
        @Email
        @NotBlank
        String email,
        @NotNull
        LocalDate dataDeNascimento,
        @NotBlank
        String telefone,

        @Pattern(regexp = "\\d{8}", message = "CEP deve conter exatamente 8 dígitos")
        String cep
) {
}
