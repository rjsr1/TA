package ta

class Evaluation {
	String title
	static hasMany = [questions:Question]
	//List<Question> questions;

	static constraints = {
        title blank: false, unique: true
    }

	public Evaluation(){
		title = null
		questions = new ArrayList<Question>()
	}

	public Question getQuestion(int index) { questions[index] }

	public boolean equals(Evaluation ev) {
		boolean equals = true;

		if (this.title != ev.title){
			equals = false
		}

		if (this.questions != null) {
			if (ev.questions != null) {

				if (this.questions.size() == ev.questions.size()){
					for (int i = 0; i < questions.size(); i += 1) {

						if (!this.questions[i].question.equals(ev.questions[i].question)) {
							equals = false
						}

						if (!this.questions[i].answer.equals(ev.questions[i].answer)) {
							equals = false
						}

						if (this.questions[i].alternatives.size() == ev.questions[i].alternatives.size()) {
							equals = false
						}
					}
				}

			} else {
				equals = false
			}
		} else {
			equals = false
		}

		return equals
	}
}