package br.com.studo.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Getter
@Setter
@Entity
@Table(name = "tab_pessoa_disciplina")
public class PessoaDisciplina implements Serializable{

    @EmbeddedId
    private PessoaDisciplinaId codigo;

    @NotNull
    private Integer ano;

}
