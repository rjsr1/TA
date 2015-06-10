package ta

class Question {

	public String question
	public List<String> alternatives
	public String answer

	public String getQuestion() { question }
	public String getAlternative(int index) { alternatives[index] }
	public String getAnswer() { answer }
}