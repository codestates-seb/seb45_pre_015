
import {useNavigate} from "react-router-dom";



const MyTokens = () => {

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


    const navigator = useNavigate();

    // navigator('/');
    window.location.replace('/')
    console.log('Login Success!');

    return null;
}

export default MyTokens;