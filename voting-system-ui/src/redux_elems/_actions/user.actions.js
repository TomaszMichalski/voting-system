import { userConstants } from 'redux_elems/_constants/user.constants';
import { userService } from 'redux_elems/_services/user.services.js';
import { alertActions } from './alert.actions.js';
import { history } from '../_helpers/history'

export const userActions = {
    register,
    login,
    logout,
    getUser
};

function login(email, password) {
    return dispatch => {
        dispatch(request({ email }));

        userService.login(email, password)
            .then(
                message => {
                    dispatch(success(message))
                    dispatch(alertActions.success(message))
                    history.push('/profile-page');
                },
                error => {
                    dispatch(failure(error));
                    dispatch(alertActions.error(error));
                }
            );
    };

    function request(user) { return { type: userConstants.LOGIN_REQUEST, user } }

    function success(user) { return { type: userConstants.LOGIN_SUCCESS, user } }

    function failure(error) { return { type: userConstants.LOGIN_FAILURE, error } }
}

function register(name, email, password) {
    return dispatch => {
        dispatch(request({ name }));

        userService.register(name, email, password)
            .then(
                message => {
                    dispatch(success(message));
                    dispatch(alertActions.success(message));
                    history.push('/login-page');
                },
                error => {
                    dispatch(failure(error));
                    dispatch(alertActions.error(error));
                }
            );
    };

    function request(user) { return { type: userConstants.REGISTER_REQUEST, user } }

    function success(user) { return { type: userConstants.REGISTER_SUCCESS, user } }

    function failure(error) { return { type: userConstants.REGISTER_FAILURE, error } }
}

function logout() {
    userService.logout();
    return { type: userConstants.LOGOUT };
}

function getUser() {
    return dispatch => {
        dispatch(request());

        userService.getUser()
            .then(
                user => {
                    dispatch(success(user));
                    dispatch(alertActions.success(user));
                },
                error => {
                    dispatch(failure(error));
                    dispatch(alertActions.error(error));
                }
            );
    };

    function request() { return { type: userConstants.GET_USER_REQUEST } }

    function success(user) { return { type: userConstants.GET_USER_SUCCESS, user } }

    function failure(error) { return { type: userConstants.GET_USER_FAILURE, error } }
}