package br.com.studo.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Getter
@Setter
@Entity
@Table(name = "tab_pessoa_disciplina")
public class PessoaDisciplina implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codigo")
    private Long codigo;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "pessoa_id")
    private Pessoa pessoa;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "disciplina_id")
    private Disciplina disciplina;

    @NotNull
    @Size(max = 4)
    @Column(name = "ano")
    private Integer ano;

}
