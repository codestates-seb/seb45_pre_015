import axios from "axios";
import { CreateQuestionData, QuestionData, UpdateQuestionData } from "../type/types";

export const fetchQuestionList = async (


  page: number,
  filter: string,
  searchText: string | null
): Promise<QuestionData[]> => {
  
    let url = 'https://659a-116-126-166-12.ngrok-free.app/questions';

    if (filter === 'vote') {
      url += '/totalVote';
    }
    
    const params = new URLSearchParams();
    
    if (filter === 'unanswered' && searchText) {
      params.set('page', String(page));
      params.set('size', '10');
      // params.set('sort', 'createdAt,desc');
      // params.set('keyword', searchText);
    } else if (filter === 'unanswered') {
      params.set('page', String(page));
      params.set('size', '10');
      // params.set('sort', 'createdAt,desc');
    } else if (filter === 'newest' && searchText) {
      params.set('page', String(page));
      params.set('size', '10');
      // params.set('sort', 'id,desc');
      // params.set('keyword', searchText);
    } else if (filter === 'newest') {
      params.set('page', String(page));
      params.set('size', '10');
      // params.set('sort', 'createdAt,desc');
    } else if (searchText) {
      params.set('page', String(page));
      params.set('size', '10');
      // params.set('keyword', searchText);
    } else {
      params.set('page', String(page));
      params.set('size', '10');
    }
    
    url += '?' + params.toString();

  try {
    const response = await fetch(url);

    if (!response.ok) {
      throw new Error('유효하지 않은 요청입니다.');
    }

    const data: QuestionData[] = await response.json();
    return data;
  } catch (error: any) {
    throw new Error(error.message);
  }
};

export const fetchCreateQuestion = async (fetchData: CreateQuestionData): Promise<number> => {
  try {
    const response = await fetch(`https://659a-116-126-166-12.ngrok-free.app/questions`, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
        'ngrok-skip-browser-warning': '69420',
        Authorization: sessionStorage.getItem('access_token') ?? '',
        Refresh: sessionStorage.getItem('access_token') ?? '',
      },
      body: JSON.stringify(fetchData),
    });

    if (response.headers.get("Authorization")!== null) {

        const accessToken = response.headers.get("Authorization");
        sessionStorage.setItem('access_token', accessToken ?? '');
      }

    if (!response.ok) {
      throw new Error('유효하지 않은 요청입니다.');
    }
    if (response.headers.get("Authorization")!== null) {

        const accessToken = response.headers.get("Authorization");
        sessionStorage.setItem('access_token', accessToken ?? '');
      }

    const data = await response.json();
    return data.id;
  } catch (error: any) {
    throw new Error(error.message);
  }
};

export const fetchUpdateQuestion = async (
    fetchData: UpdateQuestionData,
    memberId: number,
    questionId: number
): Promise<any> => {
  try {
    const response = await fetch(`http://localhost:8080/questions/${memberId}/${questionId}`, {
      method: 'PATCH',
      headers: {
        'Content-Type': 'application/json',
        Authorization: sessionStorage.getItem('access_token') ?? '',
      },
      body: JSON.stringify(fetchData),
    });

    if (!response.ok) {
      throw new Error('유효하지 않은 요청입니다.');
    }
    if (response.headers.get("Authorization")!== null) {

        const accessToken = response.headers.get("Authorization");
        sessionStorage.setItem('access_token', accessToken ?? '');
      }

    const data = await response.json();
    return data.data;
  } catch (error: any) {
    throw new Error(error.message);
  }
};

export const fetchDeleteQuestion = async (questionId: number): Promise<any> => {
  try {
    const response = await fetch(`http://localhost:8080/questions/${questionId}`, {
      method: 'DELETE',
      headers: {
        Authorization: sessionStorage.getItem('access_token') ?? '',
      },
    });

    if (!response.ok) {
      throw new Error('유효하지 않은 요청입니다.');
    }
    if (response.headers.get("Authorization")!== null) {

        const accessToken = response.headers.get("Authorization");
        sessionStorage.setItem('access_token', accessToken ?? '');
      }

    const data = await response.json();
    return data.data;
  } catch (error: any) {
    throw new Error(error.message);
  }
};