package br.com.ifba.usuario.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

/*
 * DTO para receber dados de criação ou atualização de um usuário.
 * Contém as validações de campos usando as anotações do Bean Validation.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioPostRequestDto {

    @JsonProperty(value = "nome")
    @NotNull(message = "O nome é obrigatório!")
    @NotBlank(message = "O nome não pode ser vazio!")
    private String nome;

    @JsonProperty(value = "email")
    @Email(message = "E-mail inválido!")
    private String email;

    @JsonProperty(value = "senha")
    @NotBlank (message = "A senha é obrigatória!")
    @NotNull (message = "A senha não pode ser vazia!")
    @Length(min = 8, max = 50, message = "A senha deve possuir entre 8 a 50 caracteres")
    private String senha;
}
