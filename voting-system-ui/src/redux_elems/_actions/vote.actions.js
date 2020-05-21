import { voteConstants } from 'redux_elems/_constants/vote.constants';
import { votingService } from 'redux_elems/_services/voting.services.js';
import { alertActions } from './alert.actions.js';
import { history } from '../_helpers/history'

export const votingActions = {
    vote,
    getResults,
    getOptions,
    getVotings
};

function vote(votingId, optionIds) {
    return dispatch => {
        dispatch(request({ votingId }));

        votingService.vote(votingId, optionIds)
            .then(
                message => {
                    dispatch(success(message));
                    history.push('/profile-page');
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

function getOptions(votingId) {
    return dispatch => {
        dispatch(request(votingId));

        votingService.getOptions(votingId)
            .then(
                options => dispatch(success(options)),
                error => {
                    dispatch(failure(error));
                    dispatch(alertActions.error(error))
                }
            );
    };

    function request(votingId) { return { type: voteConstants.VOTING_OPTIONS_REQUEST, votingId } }

    function success(options) { return { type: voteConstants.VOTING_OPTIONS_SUCCESS, options } }

    function failure(error) { return { type: voteConstants.VOTING_OPTIONS_FAILURE, error } }
}

function getResults(votingId) {
    return dispatch => {
        dispatch(request());

        votingService.getResults(votingId)
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