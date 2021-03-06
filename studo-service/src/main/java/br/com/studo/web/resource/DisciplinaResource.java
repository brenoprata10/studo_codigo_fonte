package br.com.studo.web.resource;

import br.com.studo.domain.Disciplina;
import br.com.studo.domain.dto.DisciplinaDTO;
import br.com.studo.service.DisciplinaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("disciplinas")
public class DisciplinaResource {

    @Autowired
    private DisciplinaService disciplinaService;

    @GetMapping
    @PreAuthorize("hasAuthority('ROLE_LISTAR_DISCIPLINA')")
    public Page<Disciplina> filtrarPesquisa(@RequestParam(required = false, defaultValue = "%") String descricao, Pageable pageable) {
        return disciplinaService.filtraPesquisa(descricao, pageable);
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ROLE_CADASTRAR_DISCIPLINA')")
    public ResponseEntity<DisciplinaDTO> salvar(@Valid @RequestBody DisciplinaDTO disciplinaDTO) {
        DisciplinaDTO disciplinaSalva = disciplinaService.salvar(disciplinaDTO);
        return disciplinaSalva != null ? ResponseEntity.status(HttpStatus.CREATED).build() : ResponseEntity.badRequest().build();
    }

    @PutMapping
    @PreAuthorize("hasAuthority('ROLE_ALTERAR_DISCIPLINA')")
    public ResponseEntity<DisciplinaDTO> alterar(@Valid @RequestBody DisciplinaDTO disciplinaDTO) {
        DisciplinaDTO disciplinaAtualizada = disciplinaService.salvar(disciplinaDTO);
        return disciplinaAtualizada != null ? ResponseEntity.status(HttpStatus.OK).body(disciplinaAtualizada) : ResponseEntity.badRequest().build();
    }

    @GetMapping("/{codigo}")
    @PreAuthorize("hasAuthority('ROLE_LISTAR_DISCIPLINA')")
    public ResponseEntity<DisciplinaDTO> buscarPorCodigo(@PathVariable Long codigo) {
        DisciplinaDTO disciplina = disciplinaService.buscarPorCodigo(codigo);
        return disciplina != null ? ResponseEntity.status(HttpStatus.OK).body(disciplina) : ResponseEntity.notFound().build();
    }

    @GetMapping("/listaTodas")
    @PreAuthorize("hasAuthority('ROLE_LISTAR_DISCIPLINA')")
    public ResponseEntity<Iterable<DisciplinaDTO>> listarTodas(){
        return ResponseEntity.ok().body(disciplinaService.listar());
    }
}
