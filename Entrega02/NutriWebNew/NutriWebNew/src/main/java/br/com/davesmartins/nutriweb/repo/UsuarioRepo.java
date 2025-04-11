package br.com.davesmartins.nutriweb.repo;

import br.com.davesmartins.nutriweb.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepo extends JpaRepository<Usuario, Integer>{

    public Optional<Usuario> findByNome(String nome);

	public Optional<Usuario> findByEmail(String email);
    

}
