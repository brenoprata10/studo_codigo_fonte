
import { Injectable } from '@angular/core';
import { AuthHttp } from 'angular2-jwt';
import { URLSearchParams } from '@angular/http';

import 'rxjs/add/operator/toPromise';

import { Turma } from './../model/turma.model';
import { STUDO_API } from '../app.api';

export class TurmaFiltro {
  ano: number;
  periodo: string;
  pagina = 0;
  itensPorPagina = 10;
}

@Injectable()
export class TurmaService {

  END_POINT = 'turmas';

  constructor(private http: AuthHttp) { }

  pesquisar(filtro: TurmaFiltro) {
    const params = new URLSearchParams();

    if (filtro.ano) {
      params.set('ano', filtro.ano.toString());
    }
    if (filtro.periodo) {
      params.set('periodo', filtro.periodo);
    }
    params.set('page', filtro.pagina.toString());
    params.set('size', filtro.itensPorPagina.toString());

    return this.http.get(`${STUDO_API}/${this.END_POINT}`, { search: params })
      .toPromise()
      .then(response => {
        const responseJson = response.json();
        const turmas = responseJson.content;

        const resultado = {
          turmas,
          total: responseJson.totalElements
        };
        return resultado;
      });
  }

  salvar(turma: Turma): Promise<any> {
    return this.http.post(`${STUDO_API}/${this.END_POINT}`, JSON.stringify(turma))
      .toPromise()
      .then(() => null);
  }

  buscarPorCodigo(codigo: number) {
    return this.http.get(`${STUDO_API}/${this.END_POINT}/${codigo}`)
      .toPromise()
      .then(response => {
        const turma = response.json() as Turma;
        return turma;
      });
  }

  buscarPorNumero(numTurma: string) {

    const params = new URLSearchParams();
    params.set('numTurma', numTurma);

    return this.http.get(`${STUDO_API}/${this.END_POINT}/buscaPorNumero`, { search: params })
      .toPromise()
      .then(response => {
        const turma = response.json() as Turma;
        return turma;
      });
  }

}
