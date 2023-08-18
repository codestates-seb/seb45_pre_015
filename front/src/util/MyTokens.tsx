


const MyTokens = () => {

    const urlParams = new URLSearchParams(window.location.search);
    const accessToken = urlParams.get('access_token');
    const refreshToken = urlParams.get('refresh_token');
    sessionStorage.setItem('access_token', accessToken ?? '');
    sessionStorage.setItem('refresh_token', refreshToken ?? '');

    window.location.replace('/')
    console.log('Login Success!');

    return null;
}

export default MyTokens;