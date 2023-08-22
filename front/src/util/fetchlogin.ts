
import { json } from 'stream/consumers';
import { showToast } from '../component/Toast';
import {isNumberObject} from "util/types";

export const fetchLogin = async (data: string): Promise<Response> => {
  try {
    const response = await fetch(``, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json;charset=UTF-8',
        Accept: 'application/json',
      },
      body: data,
    });
    
    if (!response.ok) {
      if (response.status === 401) {
        throw new Error('Wrong Email or Password');
      }
      throw new Error('Could not fetch the data for that resource');
    }

    if (response.status === 200) {
      // 토큰 저장

      const urlParams = new URLSearchParams(window.location.search);
      const accessToken = urlParams.get('access_token');
      const refreshToken = urlParams.get('refresh_token');
      sessionStorage.setItem('access_token', accessToken ?? '');
      sessionStorage.setItem('refresh_token', refreshToken ?? '');
      console.log('Login Success!');
      window.location.replace('/');
    }

    return response;
  } catch (error: any) {
    console.error(error.message);
    throw error;
  }
};

export const fetchUserInfo = async (): Promise<any> => {
  try {
    const response = await fetch(`https://659a-116-126-166-12.ngrok-free.app/members/mypage`, {
      method: 'GET',
      mode: 'cors',
      headers: {
        'Content-Type': 'application/json;charset=UTF-8',
        Accept: 'application/json',
        'ngrok-skip-browser-warning': '69420',
        Authorization: "Bearer " + sessionStorage.getItem('access_token') ?? '',
        Refresh: "Bearer " + sessionStorage.getItem('refresh_token') ?? ''


      },
      redirect: "follow",
    })

      if (!response.ok) {
        throw new Error('Could not fetch the data for that resource');
      }

      if (response.headers.get("Authorization")!== null) {

        const accessToken = response.headers.get("Authorization");
        sessionStorage.setItem('access_token', accessToken ?? '');
      }
    return response.json();

    } catch (error: any) {
    console.error(error.message);
    throw error;
  }
};

export const checkIfLogined = async (): Promise<void> => {
  try {
    const data = await fetchUserInfo();
    sessionStorage.setItem( "memberEmail",data.email);
    sessionStorage.setItem( "accountId", data.memberId);
    sessionStorage.setItem( "username", data.name);


    if (!data) {
      console.log('Please log in.');
      showToast('Please log in.', 'danger');
      setTimeout(() => {
        window.location.href = '/login';
      }, 1500);
    }
  } catch (error) {
    console.error('Error while checking login status:', error);
  }
};