package poc02

import grails.rest.*

public enum TipoEndereco {
    RESIDENCIAL, COMERCIAL
}

@Resource(readOnly = false, formats = ['json', 'xml'])
class Endereco {
    static belongsTo = [cliente: Cliente]

    long id
    TipoEndereco tipo
    String rua
    int numero
    String bairro
    String cidade

    static constraints = {
        tipo(nullable: false)
        rua(nullable: false)
        numero(nullable: false)
        bairro(nullable: false)
        cidade(nullable: false)
    }

    String toString()
    {
        return rua;
    }
}