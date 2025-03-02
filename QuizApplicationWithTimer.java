package com.logic;

import java.util.*;
public class QuizGame {
    private static final int TIME_LIMIT = 10; // Time limit per question in seconds
    private static final Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        List<Question> questions = createQuestions();
        int score = 0;
        Map<Question, Boolean> results = new HashMap<>();
        System.out.println("Welcome to the Quiz Game!");
        System.out.println("You have " + TIME_LIMIT + " seconds to answer each question.");
        for (Question question : questions) {
            boolean correct = askQuestion(question);
            results.put(question, correct);
            if (correct) score++;
        }
        displayResults(results, score);
    }
    private static List<Question> createQuestions() {
        List<Question> questions = new ArrayList<>();
        questions.add(new Question("What is the capital of INDIA?", new String[]{"A. New Delhi", "B. Karnataka", "C. Punjab", "D. Rajasthan"}, "A"));
        questions.add(new Question("Present Prime Minister of India?", new String[]{"A.Jawaharlal Nehru", "B.  Manmohan Singh ", "C. Narendra Modi", "D.  Indira Gandhi "}, "C"));
        questions.add(new Question("Who developed Java?", new String[]{"A. Microsoft", "B. Sun Microsystems", "C. Google", "D. Apple"}, "B"));
        return questions;
    }
    private static boolean askQuestion(Question question) {
        System.out.println("\n" + question.getQuestion());
        for (String option : question.getOptions()) {
            System.out.println(option);
        }
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            public void run() {
                System.out.println("\nTime's up!");
                System.exit(0);
            }
        };
        timer.schedule(task, TIME_LIMIT * 1000);
        System.out.print("Your answer: ");
        String userAnswer = scanner.next().toUpperCase();
        timer.cancel();
        if (userAnswer.equals(question.getCorrectAnswer())) {
            System.out.println("Correct!");
            return true;
        } else {
            System.out.println("Wrong! The correct answer is: " + question.getCorrectAnswer());
            return false;
        }}
    private static void displayResults(Map<Question, Boolean> results, int score) {
        System.out.println("\nQuiz Over!");
        System.out.println("Your final score: " + score + "/" + results.size());

        for (Map.Entry<Question, Boolean> entry : results.entrySet()) {
            System.out.println(entry.getKey().getQuestion() + " - " + (entry.getValue() ? "Correct" : "Incorrect"));
        }}
}
class Question {
    private final String question;
    private final String[] options;
    private final String correctAnswer;
    public Question(String question, String[] options, String correctAnswer) {
        this.question = question;
        this.options = options;
        this.correctAnswer = correctAnswer;
    }
    public String getQuestion() {
        return question;
    }
    public String[] getOptions() {
        return options;
    }
    public String getCorrectAnswer() {
        return correctAnswer;
    }
}
