package ta

class Report {
    String name
    List<Student> students

    def Report findByName(String name){
        for(Report relatorio : relatorio.findAll()){
            if(relatorio.name == name){
              return relatorio
            }
        }
    }

    static constraints = {
        name unique : true
        name nullable : false
    }
}
