import { voteConstants } from '../_constants/vote.constants';;

export function votings(state = {}, action) {
    switch (action.type) {
        case voteConstants.USER_VOTING_REQUEST:
            return {
                loading: true
            };
        case voteConstants.USER_VOTING_SUCCESS:
            return {
                votings: action.votings
            };
        case voteConstants.USER_VOTING_FAILURE:
            return {
                error: action.error
            };
        default:
            return state
    }
}