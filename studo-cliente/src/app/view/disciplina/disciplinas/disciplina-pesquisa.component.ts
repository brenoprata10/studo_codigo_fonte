import { Component, ViewChild } from '@angular/core';
import { FormControl } from '@angular/forms';
import { LazyLoadEvent } from 'primeng/components/common/api';
import { ToastyService } from 'ng2-toasty';

import { Mensagem } from '../../../util/mensagens.util';

import { DisciplinaService, DisciplinaFiltro } from '../../../service/disciplina.service';
import { Disciplina } from '../../../model/disciplina.model';
import { ErrorHandleService } from '../../../service/error-handle.service';

@Component({
  selector: 'app-disciplina-pesquisa',
  templateUrl: './disciplina-pesquisa.component.html',
  styleUrls: ['./disciplina-pesquisa.component.css']
})
export class DisciplinaPesquisaComponent {

  disciplina = new Disciplina();
  totalRegistros = 0;
  filtro = new DisciplinaFiltro();
  disciplinas = [];
  @ViewChild('tabela') grid;
  display: boolean;
  exibirhambuerger: boolean;
  cod: number;

  constructor(
    private disciplinaService: DisciplinaService,
    private toasty: ToastyService,
    private errorHandle: ErrorHandleService) { }

  pesquisar(pagina = 0) {
    this.filtro.pagina = pagina;
    this.disciplinaService.pesquisar(this.filtro)
      .then(resultado => {
        this.totalRegistros = resultado.total;
        this.disciplinas = resultado.disciplinas;
      }).catch(erro => this.errorHandle.handle(erro));
  }

  teste(dis: any) {
    this.exibirhambuerger = !this.exibirhambuerger;
    this.cod = dis.codigo;
  }

  mudarPagina(event: LazyLoadEvent) {
    const pagina = event.first / event.rows;
    this.pesquisar(pagina);
  }

  novaDisciplina() {
    this.display = true;
    this.disciplina = new Disciplina();
  }
  cancelaCadastro() {
    this.display = false;
  }

  editar(disciplina: Disciplina) {
    this.display = true;
    this.disciplina = disciplina;
  }

  salvar() {
    if (this.disciplina.codigo) {
      this.salvarAlteracao();
    } else {
      this.salvarCadastro();
    }
  }

  salvarAlteracao() {
    this.disciplinaService.alterar(this.disciplina).then(disciplina => {
      this.disciplina = disciplina;
      this.display = false;
      this.toasty.success(Mensagem.MENSAGEM_ALTERADO_SUCESSO);
    }).catch(erro => this.errorHandle.handle(erro));
  }

  salvarCadastro() {
    this.disciplina.ativa = true;
    this.disciplinaService.salvar(this.disciplina).then(() => {
      this.display = false;
      this.disciplina = new Disciplina();
      this.toasty.success(Mensagem.MENSAGEM_SALVO_SUCESSO);
      this.pesquisar();
    }).catch(erro => this.errorHandle.handle(erro));
  }

  limpa(form: FormControl) {
    form.reset();
    this.grid.reset();
    this.pesquisar();
  }
}
