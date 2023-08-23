export interface QuestionData {
  questionId: number;
    title: string;
    body: string;
    vote: number;
    view: number;
}
  
  export interface CreateQuestionData {
    title: string;
    body: string;
  }
  
  export interface UpdateQuestionData {
    title?: string;
    body?: string;
  }

  export interface TotalQuestionData {
    totalQuestions: number;
  }

  export interface answers {
    answerId: number;
    body: string;
  }

  export interface AnswerFormProps {
    questionId: number;
    onAnswerSubmit: (answer: string) => void;
  }
