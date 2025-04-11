package br.com.davesmartins.nutriweb.model.dto;

import br.com.davesmartins.nutriweb.model.Historico;
import br.com.davesmartins.nutriweb.model.Usuario;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.time.LocalDate;
import java.util.ArrayList;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CadastroUsuarioDTO {

    @NonNull
    @NotBlank
    private String nome, password1, password2;
    @Email
    @NotNull
    private String email;
    @Positive
    private double peso, altura;

    public Usuario ConvertUsuario() {

        Usuario u =Usuario.builder().nome(nome)
                .senha(password1).objetivo("Emagrecer")
                .email(email)
                .historicoList( new ArrayList<>())
                .consumoDiarioList( new ArrayList<>())
                .build();

        return u;
    }
}
