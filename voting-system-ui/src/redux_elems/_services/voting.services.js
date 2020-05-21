import config from 'config';
import { authHeader } from 'redux_elems/_helpers/auth-header';

export const votingService = {
    getVotings,
    getOptions,
    vote,
    getResults
};

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

function getVotings() {
    const requestOptions = {
        method: 'GET',
        headers: authHeader()
    };

    return fetch(`${config.apiUrl}/votings`, requestOptions).then(handleResponse);
}

function getOptions(votingId) {
    const requestOptions = {
        method: 'GET',
        headers: authHeader()
    };

    return fetch(`${config.apiUrl}/votings/${votingId}`, requestOptions).then(handleResponse);
}

function vote(votingId, optionIds) {
    const requestOptions = {
        method: 'POST',
        headers: authHeader(),
        body: JSON.stringify({ optionIds })
    };

    return fetch(`${config.apiUrl}/votings/${votingId}/votes`, requestOptions)
        .then(handleResponse)
        .then(vote => {
            return vote;
        });
}

function getResults(votingId) {
    const requestOptions = {
        method: 'GET',
        headers: authHeader()
    };

    return fetch(`${config.apiUrl}/votings/${votingId}/results`, requestOptions).then(handleResponse);
}
