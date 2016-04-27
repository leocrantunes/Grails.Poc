package poc02

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class EnderecoController {

    static responseFormats = ['json', 'xml']
    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Endereco.list(params), model:[enderecoCount: Endereco.count()]
    }

    def show(Endereco endereco) {
        respond endereco
    }

    @Transactional
    def save(Endereco endereco) {
        if (endereco == null) {
            transactionStatus.setRollbackOnly()
            render status: NOT_FOUND
            return
        }

        if (endereco.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond endereco.errors, view:'create'
            return
        }

        endereco.save flush:true

        respond endereco, [status: CREATED, view:"show"]
    }

    @Transactional
    def update(Endereco endereco) {
        if (endereco == null) {
            transactionStatus.setRollbackOnly()
            render status: NOT_FOUND
            return
        }

        if (endereco.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond endereco.errors, view:'edit'
            return
        }

        endereco.save flush:true

        respond endereco, [status: OK, view:"show"]
    }

    @Transactional
    def delete(Endereco endereco) {

        if (endereco == null) {
            transactionStatus.setRollbackOnly()
            render status: NOT_FOUND
            return
        }

        endereco.delete flush:true

        render status: NO_CONTENT
    }
}
