package ta

/**
 * Created by Leonardo on 10/06/2015.
 */
class Alternative {
    String alternative

    static belongsTo = Question
    static hasMany = [questions:Question]

    static constraints = {
        alternative blank: false, unique: true
    }
}
