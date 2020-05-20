import { voteConstants } from 'redux_elems/_constants/vote.constants';
import { userService } from 'redux_elems/_services/user.services.js';
import { votingService } from 'redux_elems/_services/voting.services.js';
import { alertActions } from './alert.actions.js';
import { history } from '../_helpers/history'

export const votingActions = {
    vote,
    getResults,
    getOptions,
    getVotings
};

function vote(candidate) {
    return dispatch => {
        dispatch(request({ candidate }));

        userService.vote(candidate)
            .then(
                vote => {
                    dispatch(success(vote));
                    history.push('/result-page');
                },
                error => {
                    dispatch(failure(error));
                    dispatch(alertActions.error(error));
                }
            );
    };

    function request(vote) { return { type: voteConstants.VOTING_REQUEST, vote } }

    function success(vote) { return { type: voteConstants.VOTING_SUCCESS, vote } }

    function failure(error) { return { type: voteConstants.VOTING_FAILURE, error } }
}

function getOptions() {
    return dispatch => {
        dispatch(request());

        userService.getOptions()
            .then(
                options => dispatch(success(options)),
                error => {
                    dispatch(failure(error));
                    dispatch(alertActions.error(error))
                }
            );
    };

    function request() { return { type: voteConstants.VOTING_OPTIONS_REQUEST } }

    function success(options) { return { type: voteConstants.VOTING_OPTIONS_SUCCESS, options } }

    function failure(error) { return { type: voteConstants.VOTING_OPTIONS_FAILURE, error } }
}

function getResults() {
    return dispatch => {
        dispatch(request());

        userService.getResults()
            .then(
                results => dispatch(success(results)),
                error => {
                    dispatch(failure(error));
                    dispatch(alertActions.error(error))
                }
            );
    };

    function request() { return { type: voteConstants.VOTING_RESULT_REQUEST } }

    function success(results) { return { type: voteConstants.VOTING_RESULT_SUCCESS, results } }

    function failure(error) { return { type: voteConstants.VOTING_RESULT_FAILURE, error } }
}

function getVotings() {
    return dispatch => {
        dispatch(request());

        votingService.getVotings()
            .then(
                votings => dispatch(success(votings)),
                error => {
                    dispatch(failure(error));
                    dispatch(alertActions.error(error))
                }
            );
    };

    function request() { return { type: voteConstants.USER_VOTING_REQUEST } }

    function success(votings) { return { type: voteConstants.USER_VOTING_SUCCESS, votings } }

    function failure(error) { return { type: voteConstants.USER_VOTING_FAILURE, error } }
}