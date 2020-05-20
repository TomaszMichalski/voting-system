import config from 'config';
import { authHeader } from 'redux_elems/_helpers/auth-header';

export const userService = {
    register,
    login,
    logout,
    vote,
    getResults,
    getOptions
};

function login(email, password) {
    const requestOptions = {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ email, password })
    };

    return fetch(`http://localhost:8080/api/auth/login`, requestOptions)
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

    return fetch(`http://localhost:8080/api/auth/register`, requestOptions)
        .then(handleResponse);
}

function logout() {
    localStorage.removeItem('token');
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

function vote(candidate) {
    const requestOptions = {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ candidate })
    };

    return fetch(`${config.apiUrl}/voting/election`, requestOptions)
        .then(handleResponse)
        .then(vote => {
            return vote;
        });
}

function getResults() {
    const requestOptions = {
        method: 'GET',
        headers: authHeader()
    };

    return fetch(`${config.apiUrl}/voting/election/results`, requestOptions).then(handleResponse);
}

function getOptions() {
    const requestOptions = {
        method: 'GET',
        headers: authHeader()
    };

    return fetch(`${config.apiUrl}/voting/election/options`, requestOptions).then(handleResponse);
}
