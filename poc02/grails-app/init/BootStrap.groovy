import poc02.Cliente
import poc02.Endereco
import poc02.TipoEndereco

class BootStrap {

    def init = { servletContext ->

        /**
         * Criação do cliente
         */
        def cliente = new Cliente(nome: 'Leo Antunes', dataNasc: new Date(),
                email: 'leocrantunes@gmail.com', idade: 26)
        cliente.save(failOnError: true)

        /**
         * Criação do primeiro endereço
         */
        def endereco1 = new Endereco(rua: 'Estrada do Rio Grande', numero: 2034,
                bairro: 'Taquara', cidade: 'Rio de Janeiro',
                tipo: TipoEndereco.RESIDENCIAL)
        endereco1.setCliente(Cliente.findByNome('Leo Antunes'))
        endereco1.save(failOnError: true)

        /**
         * Criação do segundo endereço
         */
        def endereco2 = new Endereco(rua: 'Avenida Marechal Camara',
                numero: 160, bairro: 'Centro', cidade: 'Rio de Janeiro',
                tipo: TipoEndereco.COMERCIAL)
        endereco2.setCliente(Cliente.findByNome('Leo Antunes'))
        endereco2.save(failOnError: true)
    }

    def destroy = {
    }
}
