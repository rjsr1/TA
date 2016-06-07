package ta

import static org.springframework.http.HttpStatus.NOT_FOUND
import static org.springframework.http.HttpStatus.OK

class ReportController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Report.list(params), model: [ReportInstanceCount : Report.count()]
    }

   def show(Report reportInstance){
       respond reportInstance
   }

    def createSaveResetResponse(){
        create()
        save()
        response.reset()
    }

    def create(){
        respond new Report(params)
    }

    public boolean saveRep(Report relatorio){
        if(relatorio.findByName(relatorio.name)==null){
                notFound()
                return false//não é para renderizar nenhuma view porque esses relatórios não são feitos pelo usuário, apenas mostrados
        }
        relatorio.save(flush: true)
        return true
    }

    def save(){
        def reportInstance = new Report(params)
        if(!reportInstance.save(flush:true)){
            render (view: "show", model: [reportInstance: reportInstance])
            return
        }
        if(reportInstance.hasErrors()){
            respond reportInstance.errors, view:'show'
        }

        reportInstance.save(flush: true)
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'Report.label', default: 'Report'), reportInstance.id])
                redirect reportInstance
            }
            '*'{ respond reportInstance, [status: OK] }
        }
    }

    def update(Report reportInstance) {
        if (reportInstance == null) {
            notFound()
            return
        }

        if (reportInstance.hasErrors()) {
            respond reportInstance.errors, view:'show'
            return
        }

        reportInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'Report.label', default: 'Report'), reportInstance.id])
                redirect reportInstance
            }
            '*'{ respond reportInstance, [status: OK] }
        }
    }

    def findByName(String nome){
        return Report.findByName(nome)
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'report.label', default: 'Report'), params.id])
                redirect action: "show", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
