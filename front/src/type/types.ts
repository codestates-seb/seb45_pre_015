export interface QuestionData {
  questionId: number;
    title: string;
    body: string;
}
  
  export interface CreateQuestionData {
    title: string;
    content: string;
  }
  
  export interface UpdateQuestionData {
    title?: string;
    content?: string;
  }
