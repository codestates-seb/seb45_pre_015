export interface QuestionData {
  quantity: number;
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
