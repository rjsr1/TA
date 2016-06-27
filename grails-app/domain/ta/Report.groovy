package ta

class Report {
    String name
    List students
    static hasMany = [students: Student]
    String tipo
    double valor
    String avaliacao

    public Report(String name, String tipo, double valor, String avaliacao){
        this.name = name
        this.tipo = tipo
        this.students = []
        this.valor = valor
        this.avaliacao = avaliacao

    }

    static constraints = {
        name unique : true
        name nullable : false
        tipo inList: ["Porcentagem","Media"], nullable: false
        avaliacao inList: ["MA", "MPA", "MANA"]
    }

    static mapping ={
        sort "name"
        sort name:"asc"
    }
}
