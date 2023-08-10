export const fetchLogin = async (data: string): Promise<Response> => {
    try {
      const response = await fetch(`/auth/login`, {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json;charset=UTF-8',
          Accept: 'application/json',
        },
        body: data,
      });
  
      if (!response.ok) {
        console.log(response);
        if (response.status === 401) {
          console.error('Wrong Email or Password');
        }
        throw new Error('could not fetch the data for that resource');
      }
  
      if (response.status === 200) {
        console.log('Login Success!');
  
        // 토큰 저장
        const accessToken = response.headers.get('Authorization');
        const refreshToken = response.headers.get('refresh');
        sessionStorage.setItem('access_token', accessToken ?? '');
        sessionStorage.setItem('refresh_token', refreshToken ?? '');
      }
  
      return response;
    } catch (error:any) {
      console.error(error.message);
      throw error;
    }
  };
  
  //조회
  export const fetchUserInfo = async (): Promise<any> => {
    try {
      const response = await fetch(`/accounts/user`, {
        method: 'GET',
        headers: {
          'Content-Type': 'application/json;charset=UTF-8',
          Accept: 'application/json',
          authorization: sessionStorage.getItem('access_token') ?? '',
        },
      });
  
      if (!response.ok) {
        throw new Error('could not fetch the data for that resource');
      }
  
      return response.json();
    } catch (error:any) {
      console.error(error.message);
      throw error;
    }
  };
  
  export const checkIfLogined = async (): Promise<void> => {
    try {
      const data = await fetchUserInfo();
  
      if (!data) {
        setTimeout(() => {
          window.location.href = '/login';
        }, 1500);
        console.log('Please log in.');
      }
    } catch (error) {
      console.error(error);
    }
  };