import { showToast } from '../component/Toast';

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
      // localStorage.setItem("access_token", accessToken);
      // localStorage.setItem("refresh_token", refreshToken);
      //
      // const accessToken = response.headers.get('Authorization');
      // const refreshToken = response.headers.get('refresh');
      sessionStorage.setItem('access_token', accessToken ?? '');
      sessionStorage.setItem('refresh_token', refreshToken ?? '');
      console.log('Login Success!');

      window.location.replace('/header')
    }

    return response;
  } catch (error: any) {
    console.error(error.message);
    throw error;
  }
};

// 조회
export const fetchUserInfo = async (): Promise<any> => {
  try {
    const response = await fetch(`/members/mypage`, {
      method: 'GET',
      headers: {
        'Content-Type': 'application/json;charset=UTF-8',
        Accept: 'application/json',
        authorization: sessionStorage.getItem('access_token') ?? '',
        //Refresh 토큰도 같이 헤더에 담아서 요청 보내주세요.
      },
    });

    if (!response.ok) {
      throw new Error('Could not fetch the data for that resource');
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