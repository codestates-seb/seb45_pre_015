export interface QuestionData {
    id: number;
    title: string;
    content: string;
  }
  
  export interface CreateQuestionData {
    title: string;
    content: string;
  }
  
  export interface UpdateQuestionData {
    title?: string;
    content?: string;
  }