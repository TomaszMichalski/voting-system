import { userConstants } from '../_constants/user.constants';

export function authorization(state = {}, action) {
    switch (action.type) {
        case userConstants.REGISTER_REQUEST:
            return {
                loading: true
            };
        case userConstants.REGISTER_SUCCESS:
            return {
                loading: false,
                registred: true
            };
        case userConstants.REGISTER_FAILURE:
            return {
                loading: false,
                registred: false
            };
        case userConstants.LOGOUT:
            return {
                registred: false
            };
        default:
            return state
    }
}