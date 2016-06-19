package ta

class Report {
    String name
    List<Student> students
    String tipo
    double valor
    String avaliacao

    public Report(String name, String tipo, double valor, String avaliacao){
        this.name = name
        this.tipo = tipo
        this.students = new LinkedList<Student>()
        this.valor = valor
        this.avaliacao = avaliacao

    }
    /*def Report findByName(String name){
        for(Report relatorio : Report.findAll()){
            if(relatorio.name == name){
              return relatorio
            }
        }
    }*/

    static constraints = {
        name unique : true
        name nullable : false
        tipo inList: ["Porcentagem","Média"], nullable: false
        avaliacao inList: ["MA", "MPA", "MANA"]
    }

    static mapping ={
        sort "name"
        sort name:"asc"
    }
}
