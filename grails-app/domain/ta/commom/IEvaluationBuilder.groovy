package ta.commom

import ta.Evaluation

public interface IEvaluationBuilder {

	public void createEvaluation()
	public void setEvaluationTitle(String title)
	public int addEvaluationQuestion(String question)
	public void setQuestionAnswer(int questionIndex, String answer)
	public void addQuestionAlternative(int questionIndex, String alternative)
	public Evaluation getEvaluation()

}