import { combineReducers } from 'redux';
import { alert } from './alert.reducer';
import { authentication } from './authentication.reducer';
import { option } from './options.reducer';
import { result } from './result.reducer';
import { getUser } from './user.reducer';
import { voting } from './vote.reducer';
import { authorization } from './authorization.reducer';
import { votings } from './voting.reducer';

const reducer = combineReducers({
    alert, 
    authentication,
    authorization,
    option,
    result,
    getUser,
    voting,
    votings
});

export default reducer;