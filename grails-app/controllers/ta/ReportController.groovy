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
        save(flush: true)
        response.reset()
    }

    def create(){
        respond new Report(params)
    }

    public void addStudentToReport(String login, String nome){
        Report repo = findByName(nome)
        repo.addStudentToReport(login,nome)
        respond update(repo)
    }

    public boolean saveRep(Report relatorio){
        if(relatorio.findByName(relatorio.name)==null){
                notFound()
                return false//não é para renderizar nenhuma view porque esses relatórios não são feitos pelo usuário, apenas mostrados
        }
        relatorio.save(flush: true)
        return true
    }
    def delete(Report reportInstance) {

        if (reportInstance == null) {
            notFound()
            return
        }

        reportInstance.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'Report.label', default: 'Report'), reportInstance.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
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

    def edit(Student studentInstance) {
        respond studentInstance
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
