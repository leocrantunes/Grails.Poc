package poc02

import grails.rest.*

@Resource(readOnly = false, formats = ['json', 'xml'])
class Cliente {
    static hasMany = [enderecos: Endereco]

    long id
    String nome
    String email
    Date dataNasc
    int idade

    static constraints = {
        nome(nullable: false)
        email(nullable: false)
        dataNasc(nullable: false)
        idade(nullable: false)
    }

    String toString()
    {
        return nome;
    }
}