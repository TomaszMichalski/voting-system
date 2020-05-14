import { voteConstants } from '../_constants/vote.constants';;

export function option(state = {}, action) {
    switch (action.type) {
        case voteConstants.VOTING_OPTIONS_REQUEST:
            return {
                loading: true
            };
        case voteConstants.VOTING_OPTIONS_SUCCESS:
            return {
                options: action.options
            };
        case voteConstants.VOTING_OPTIONS_FAILURE:
            return {
                error: action.error
            };
        default:
            return state
    }
}