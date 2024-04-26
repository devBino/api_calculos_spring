package br.com.api.calculos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.api.calculos.model.MUsuario;

/**
 * Repositorio para manipular entidade usuario
 */
@Repository
public interface UsuarioRepository extends JpaRepository<MUsuario, Long> {
    
    @Query("from MUsuario where user = :user and password = :password")
    MUsuario findUserByCredentials(@Param("user") String user, @Param("password") String password);

    @Query("from MUsuario where user = :user")
    MUsuario findUserByDescUser(@Param("user") String user);
    
}
