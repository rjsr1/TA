package commom

import ta.Evaluation

class EvaluationBuilder implements IEvaluationBuilder {

	private Evaluation _Evaluation

	public void createEvaluation() {
		_Evaluation = new Evaluation()
	}

	public void setEvaluationTitle(String title) {
		_Evaluation.title = title
	}

	public int addEvaluationQuestion(String question) {
		_Evaluation.questions.add(question)
		_Evaluation.questions.size() - 1
	}

	public void setQuestionAnswer(int questionIndex, String answer) {
		_Evaluation.questions[questionIndex].answer = answer
	}

	public void addQuestionAlternative(int questionIndex, String alternative){
		_Evaluation.questions[questionIndex].alternatives.add(alternative)
	}

	public Evaluation getEvaluation() {
		return _Evaluation
	}
}