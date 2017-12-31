import { Injectable } from '@angular/core';
import { Http, Headers, URLSearchParams } from '@angular/http';
import { STUDO_API } from '../app.api';
import 'rxjs/add/operator/toPromise';

export class DisciplinaFiltro {
  descricao: string;
  pagina = 0;
  itensPorPagina = 10;
}

@Injectable()
export class DisciplinaService {

  END_POINT = 'disciplinas';

  constructor(private http: Http) { }

  pesquisar(filtro: DisciplinaFiltro): Promise<any> {
    const params = new URLSearchParams();
    const headers = new Headers();

    params.set('page', filtro.pagina.toString());
    params.set('size', filtro.itensPorPagina.toString());

    if (filtro.descricao) {
      params.set('descricao', filtro.descricao);
    }

    return this.http.get(`${STUDO_API}/${this.END_POINT}`, { headers, search: params })
      .toPromise()
      .then(response => {
        const responseJson = response.json();
        const disciplinas = responseJson.content;

        const resultado = {
          disciplinas,
          total: responseJson.totalElements
        };
        return resultado;
      });
  }

}
