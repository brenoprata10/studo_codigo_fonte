package br.com.studo.service;

import br.com.studo.domain.Turma;
import br.com.studo.domain.dto.TurmaDTO;
import br.com.studo.domain.enums.Periodo;
import br.com.studo.domain.mapper.TurmaMapper;
import br.com.studo.exception.StudoException;
import br.com.studo.repository.TurmaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class TurmaService {

    @Autowired
    private TurmaRepository turmaRepository;

    @Autowired
    private TurmaMapper turmaMapper;

    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public Page<Turma> filtarPesquisa(List<Periodo> periodos, Integer ano, Pageable pageable) {
        return turmaRepository.findPeriodoAndAno(periodos, ano, pageable);
    }

    public TurmaDTO salvar(TurmaDTO turmaDTO) {
        turmaDTO.setNumeroTurma(gerarNumeroTurma(turmaDTO));
        if (turmaDTO.getCodigo() == null) {
            verificaTurmaCadastrada(turmaDTO.getNumeroTurma());
        }
        return turmaMapper.toDTO(turmaRepository.save(turmaMapper.toEntity(turmaDTO)));
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public Turma buscarPorCodigo(Long codigo) {
        return turmaRepository.findOne(codigo);
    }

    private String gerarNumeroTurma(TurmaDTO turmaDTO) {
        return new StringBuilder()
                .append(turmaDTO.getPeriodo().name().substring(0, 1))
                .append(turmaDTO.getAno())
                .append(turmaDTO.getSerie().substring(0, 1))
                .append(turmaDTO.getDescricaoTurma())
                .toString();
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public TurmaDTO buscaTurmaPorNumero(String numTurma) {
        return turmaMapper.toDTO(turmaRepository.findByNumeroTurma(numTurma));
    }

    private void verificaTurmaCadastrada(String numTruma) {
        if (turmaRepository.findTurmaCadastrada(numTruma)) {
            throw new StudoException("Turma já cadastrada!");
        }
    }

}
