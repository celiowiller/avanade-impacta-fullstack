// importar o model de leitura 
import { LeituraModel } from "./leitura.model";

// aqui, vamos definir a enum para os tipo de sensor que a aplicação vai tratar
export enum TipoSensor{
    SOLO = 'SOLO',
    CLIMA = 'CLIMA',
    NIVEL_TANQUE = 'NIVEL_TANQUE',
    PH = 'PH'
}

// definir o historico de movimentação do sensor (queremos "espelhar"  a entity SensorLocalizacaoEntity do backend)
export interface SensorLocalizacao{
    id: string
    localizacao: string
    dataInicio: string // LocalDateTime, aqui, se torna string ISO no Json
    dataFim?: string // valor de prop Opcional; pois a localização atual tem dataFim com possibilidade de nulidade NULL
}

// este é o model domain principal
export interface SensorModel {
    id: string
    nome: string
    localizacao: string // localização atual simplificada
    
    // definir, abaixo, novas props para "suportar" os Usescases de Dashboard e Historico
    historico?: SensorLocalizacao[]
    leituras?: LeituraModel[]
}
