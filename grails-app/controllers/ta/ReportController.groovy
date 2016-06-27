package ta

import static org.springframework.http.HttpStatus.NOT_FOUND
import static org.springframework.http.HttpStatus.OK

class ReportController {

    static allowedMethods = [update: "PUT"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Report.list(params), model: [ReportInstanceCount : Report.count()]
    }

   def show(Report reportInstance){
       respond reportInstance
   }

    def create(){
        respond new Report(params)
    }

    public void addStudentToReport(Student student, Report reportInstance){
        if(reportInstance!=null){
            reportInstance.addToStudents(student)
            reportInstance.save(flush: true)
            flash.message = message(code: 'default.updated.message', args:[message(code: 'Report.label', default: 'Report'), reportInstance.id])
        }
    }
    def fillReport(Report reportInstance) {
        StudentController sc = new StudentController()
        for (int i = 0; i < Student.list().size(); i++) {
            if (reportInstance.tipo.equalsIgnoreCase("Porcentagem")) {
                sc.checkConditionPercentage(Student.list().get(i).login, reportInstance)
            } else {
                sc.checkConditionAverage(Student.list().get(i), reportInstance)
            }
        }
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
        fillReport(reportInstance)
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

    def edit(Report reportInstance) {
        respond reportInstance
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
