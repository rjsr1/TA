package ta

class Question {

	static belongsTo = Evaluation
	static hasMany = [evaluations:Evaluation, alternatives:Alternative]

	String question
	//List<String> alternatives
	String answer

	static constraints = {
		question blank: false, unique: true
	}
}