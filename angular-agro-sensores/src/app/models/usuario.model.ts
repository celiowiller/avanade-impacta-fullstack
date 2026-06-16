// temos de importar aqui, as roles do usuario
import { UserRoleModel } from "./user-role.model";

// regras estabelecidas para os dados referentes ao usuario
export interface UsuarioModel {
    id: string // o caractere ( : ) indica que estamos definindo, para a prop, um data type especifico - neste caso -> string 
    login: string
    senha: string
    role: UserRoleModel
}
