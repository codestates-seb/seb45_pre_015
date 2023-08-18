import axios from 'axios';

const API_URL = 'https://659a-116-126-166-12.ngrok-free.app/answers';

export const postAnswer = async (answerText) => {
  const requestBody = {
    body: answerText
  };

  const headers = {
    'Content-Type': 'application/json;charset=UTF-8',
    Accept: 'application/json',
    Authorization: "Bearer " + sessionStorage.getItem('access_token') ?? '',
    Refresh: "Bearer " + sessionStorage.getItem('refresh_token') ?? ''
  };

  try {
    const response = await axios.post(API_URL, requestBody, { headers });
    return response.data;
  } catch (error) {
    throw error;
  }
};