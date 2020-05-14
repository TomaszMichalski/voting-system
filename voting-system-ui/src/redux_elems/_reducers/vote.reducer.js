import { voteConstants } from '../_constants/vote.constants';

export function voting(state = {}, action) {
    switch (action.type) {
        case voteConstants.VOTING_REQUEST:
            return {
                loading: true
            };
        case voteConstants.VOTING_SUCCESS:
            return {
                vote: action.vote
            };
        case voteConstants.VOTING_FAILURE:
            return {
                error: action.error
            };
        default:
            return state
    }
}