import { userConstants } from '../_constants/user.constants';

let token = localStorage.getItem('token');
const initialState = token ? { loggedIn: false, token } : {};

export function authentication(state = initialState, action) {
    switch (action.type) {
        case userConstants.LOGIN_REQUEST:
            return {
                loading: true,
            };
        case userConstants.LOGIN_SUCCESS:
            return {
                loading: false,
                loggedIn: true,
                token: action.user.accessToken
            };
        case userConstants.LOGIN_FAILURE:
            return {
                loading: false
            };
        default:
            return state
    }
}