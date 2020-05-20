import React from "react";
import { connect } from 'react-redux';
import { Router, Route, Switch } from "react-router-dom";

import { history } from 'redux_elems/_helpers/history';

import { ProfilePage } from "views/ProfilePage/ProfilePage.js";
import { LoginPage } from "views/LoginPage/LoginPage.js";
import { VotingPage } from "views/VotingPage/VotingPage.js";
import { HomePage } from "views/HomePage/HomePage.js";
import NotFound from "views/NotFoundPage/NotFound.js";
import { ResultPage } from "views/ResultPage/ResultPage";
import { alertActions } from "redux_elems/_actions/alert.actions";

function App(props) {
  const { dispatch, alert } = props;

  history.listen((location, action) => {
    dispatch(alertActions.clear());
  });

  return (
    <Router history={history}>
      <Switch>
        <Route exact path="/" component={HomePage} />
        <Route path="/profile-page" component={ProfilePage} />
        <Route path="/login-page" component={LoginPage} />
        <Route path="/voting-page" component={VotingPage} />
        <Route path="/result-page" component={ResultPage} />
        <Route path="*" component={NotFound} />
      </Switch>
    </Router>
  );
}

function mapStateToProps(state) {
  const { alert } = state;
  return {
      alert
  };
}

const connectedApp = connect(mapStateToProps)(App);
export { connectedApp as App }; 
