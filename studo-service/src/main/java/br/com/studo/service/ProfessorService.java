package br.com.studo.service;

import br.com.studo.domain.Professor;
import br.com.studo.domain.dto.ProfessorDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProfessorService {

    Page<Professor> filtarPesquisar(String nome, Pageable pageable);

    ProfessorDTO salvar(ProfessorDTO professorDTO);

    boolean verificaCpfCadastrado(String cpf);

    ProfessorDTO buscaPorCodigo(Long codigo);

    Integer count();

   ProfessorDTO buscarProfessorLogado();

   Long buscaCodProfessorProCPF(String cpf);
}
