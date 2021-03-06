package br.com.studo.repository;

import br.com.studo.domain.Professor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfessorRepository extends CrudRepository<Professor, Long> {

    Page<Professor> findByNomeStartingWithIgnoreCase(String nome, Pageable pageable);

    Professor findByCpf(String cpf);

    @Query("SELECT count(*) FROM Professor")
    Integer quantidade();

    @Query("SELECT p.codigo FROM Professor p WHERE p.cpf = ?1")
    Long buscaCodProfessorPorCPF(String cpf);
}
