export interface LeituraModel {
    id?: number // '?' indica que o backend irá gerar quando a requisição POST for feita
    valor: number
    dataHora: string // aqui, recebemos como ISO String a partir do LocalDateTime
    localizacao: string
}

// também, precisamos definir o sensor com as leituras
export interface SensorComLeituras{
    id: string
    nome: string
    localizacaoAtual: string
    leituras: LeituraModel[]
    historico?: any[]
}
