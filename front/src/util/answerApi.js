import axios from 'axios';

const API_URL = 'https://659a-116-126-166-12.ngrok-free.app/answers';

export const postAnswer = async (body) => {
  const requestBody = {
    body: body
  };

  const headers = {
    'Content-Type': 'application/json;charset=UTF-8',
    Accept: 'application/json',
    'ngrok-skip-browser-warning': '69420',
    Authorization: "Bearer " + sessionStorage.getItem('access_token') ?? '',
    Refresh: "Bearer " + sessionStorage.getItem('refresh_token') ?? ''
  };

  try {
    const response = await axios.post(API_URL, requestBody, { headers });

    if (response.headers.get("Authorization") !== null) {
      const accessToken = response.headers.get("Authorization");
      sessionStorage.setItem('access_token', accessToken ?? '');
    }

    if (!response.ok) {
      throw new Error('유효하지 않은 요청입니다.');
    }

    return response.data;
  } catch (error) {
    throw error;
  }
};
