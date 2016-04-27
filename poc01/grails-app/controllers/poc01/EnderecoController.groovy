package poc01

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class EnderecoController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Endereco.list(params), model:[enderecoCount: Endereco.count()]
    }

    def show(Endereco endereco) {
        respond endereco
    }

    def create() {
        respond new Endereco(params)
    }

    @Transactional
    def save(Endereco endereco) {
        if (endereco == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (endereco.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond endereco.errors, view:'create'
            return
        }

        endereco.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'endereco.label', default: 'Endereco'), endereco.id])
                redirect endereco
            }
            '*' { respond endereco, [status: CREATED] }
        }
    }

    def edit(Endereco endereco) {
        respond endereco
    }

    @Transactional
    def update(Endereco endereco) {
        if (endereco == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (endereco.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond endereco.errors, view:'edit'
            return
        }

        endereco.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'endereco.label', default: 'Endereco'), endereco.id])
                redirect endereco
            }
            '*'{ respond endereco, [status: OK] }
        }
    }

    @Transactional
    def delete(Endereco endereco) {

        if (endereco == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        endereco.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'endereco.label', default: 'Endereco'), endereco.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'endereco.label', default: 'Endereco'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
