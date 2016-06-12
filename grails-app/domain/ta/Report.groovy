package ta

class Report {
    String name
    List<Student> students

    public Report(String name){
        this.name = name
        this.students = new LinkedList<Student>()
    }
    def Report findByName(String name){
        for(Report relatorio : Report.findAll()){
            if(relatorio.name == name){
              return relatorio
            }
        }
    }

    public void addStudentToReport(String studentLogin, String reportName){
        Student student = Student.findByLogin(studentLogin)
        Report report = findByName(reportName)
        report.students.add(student)
    }

    static constraints = {
        name unique : true
        name nullable : false
    }
}
