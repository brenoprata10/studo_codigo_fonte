package br.com.studo.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@Entity
@Table(name = "tab_endereco",  schema = "studo")
public class Endereco implements Serializable {

    private static final long serialVersionUID = 1454354308295534370L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codigo")
    private Long codigo;

    @NotNull
    @Size(max = 8)
    @Column(name = "cep")
    private String cep;

    @NotNull
    @Size(max = 5)
    @Column(name = "estado")
    private String uf;

    @NotNull
    @Size(max = 50)
    @Column(name = "localidade")
    private String localidade;

    @NotNull
    @Size(max = 50)
    @Column(name = "logradouro")
    private String logradouro;

    @NotNull
    @Size(max = 50)
    @Column(name = "bairro")
    private String bairro;

    @NotNull
    @Size(max = 6)
    @Column(name = "numero")
    private String numero;

    @Size(max = 100)
    @Column(name = "complemento")
    private String complemento;

}
