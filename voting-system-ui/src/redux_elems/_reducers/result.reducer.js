import { voteConstants } from '../_constants/vote.constants';;

export function result(state = {}, action) {
    switch (action.type) {
        case voteConstants.VOTING_RESULT_REQUEST:
            return {
                loading: true
            };
        case voteConstants.VOTING_RESULT_SUCCESS:
            return {
                results: action.results
            };
        case voteConstants.VOTING_RESULT_FAILURE:
            return {
                error: action.error
            };
        default:
            return state
    }
}