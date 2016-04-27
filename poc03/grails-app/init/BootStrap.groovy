import poc03.*

class BootStrap {

    def init = { servletContext ->
        /**
         * Criação do cliente
         */
        def cliente = new Cliente(nome: 'Leo Antunes', dataNasc: new Date(),
                email: 'leocrantunes@gmail.com', idade: 26)
        cliente.save(failOnError: true)
    }

    def destroy = {
    }
}
