import { userConstants } from '../_constants/user.constants';

export function user(state = {}, action) {
    switch (action.type) {
        case userConstants.GET_USER:
            return {
                email: action.email,
                name: action.name
            };
        default:
            return state
    }
}