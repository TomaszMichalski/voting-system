import { combineReducers } from 'redux';
import { alert } from './alert.reducer';
import { authentication } from './authentication.reducer';
import { option } from './options.reducer';
import { result } from './result.reducer';
import { users } from './users.reducer';
import { voting } from './vote.reducer';

const reducer = combineReducers({
    alert, 
    authentication,
    option,
    result,
    users,
    voting
});

export default reducer;