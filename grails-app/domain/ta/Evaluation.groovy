package ta

class Evaluation {
	String title
	List<Question> questions

	static constraints = {
        title blank: false
    }

	public Question getQuestion(int index) { questions[index] }
}