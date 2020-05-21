import config from 'config';
import { authHeader } from 'redux_elems/_helpers/auth-header';

export const userService = {
    register,
    login,
    logout,
    getUser
};

function login(email, password) {
    const requestOptions = {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ email, password })
    };

    return fetch(`${config.apiUrl}/auth/login`, requestOptions)
        .then(handleResponse)
        .then(token => {
            localStorage.setItem('token', token.accessToken);
            return token;
        })
}

function register(name, email, password) {
    const requestOptions = {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ name, email, password })
    };

    return fetch(`${config.apiUrl}/auth/register`, requestOptions)
        .then(handleResponse);
}

function logout() {
    localStorage.removeItem('token');
}

function getUser() {
    const requestOptions = {
        method: 'GET',
        headers: authHeader()
    };

    return fetch(`${config.apiUrl}/users/me`, requestOptions).then(handleResponse);
}

function handleResponse(response) {
    return response.text().then(text => {
        const data = text && JSON.parse(text);
        if (!response.ok) {
            if (response.status === 401) {
                // auto logout if 401 response returned from api
                logout();
                location.reload(true);
            }

            const error = (data && data.message) || response.statusText;
            return Promise.reject(error);
        }

        return data;
    });
}
