import React, { useEffect } from 'react';

const MyRefreshToken = () => {
    useEffect(() => {
        // Get the URL parameters
        const urlParams = new URLSearchParams(window.location.search);
        const accessToken = urlParams.get('access_token');
        const refreshToken = urlParams.get('refresh_token');

        // Update session storage with the new tokens
        if (accessToken && refreshToken) {
            sessionStorage.setItem('access_token', accessToken);
            sessionStorage.setItem('refresh_token', refreshToken);
        }

        // Redirect to the main page after updating tokens
        window.location.replace('/');
    }, []);

    return null;
};

export default MyRefreshToken;
