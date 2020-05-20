import React, { useEffect } from "react";
import { useDispatch, useSelector } from "react-redux";
// nodejs library that concatenates classes
import classNames from "classnames";
// @material-ui/core components
import { makeStyles } from "@material-ui/core/styles";
import { Redirect } from "react-router-dom";

import { votingActions } from 'redux_elems/_actions/vote.actions.js';

// core components;
import Footer from "components/Footer/Footer.js";
import GridContainer from "components/Grid/GridContainer.js";
import GridItem from "components/Grid/GridItem.js";
import Menu from "components/Menu/Menu.js";
import Parallax from "components/Parallax/Parallax.js";

import avatar from "assets/img/avatar.jpg";
import styles from "assets/jss/material-kit-react/views/profilePage.js";

const useStyles = makeStyles(styles);

export const ProfilePage = () => {
  const classes = useStyles();
  const imageClasses = classNames(
    classes.imgRaised,
    classes.imgRoundedCircle,
    classes.imgFluid
  );
  const user = useSelector((state) => state.user);
  const votings = useSelector((state) => state.votings.votings);
  console.log(votings)
  const dispatch = useDispatch();
  useEffect(() => dispatch(votingActions.getVotings()), []);

  const token = localStorage.getItem("token");
  return token ? (
    <div>
      <Menu />
      <Parallax small filter image={require("assets/img/profile-bg.jpg")} />
      <div className={classNames(classes.main, classes.mainRaised)}>
        <div>
          <div className={classes.container}>
            <GridContainer justify="center">
              <GridItem xs={12} sm={12} md={6}>
                <div className={classes.profile}>
                  <div>
                    <img src={avatar} alt="..." className={imageClasses} />
                  </div>
                  <div className={classes.name}>
                    <h3 className={classes.title}>{`Welcome ${user.name || user.email}!`}</h3>
                  </div>
                </div>
              </GridItem>
            </GridContainer>
            <div className={classes.description}>
              <h3>See your voting:</h3>
                {
                  votings ? 
                  <ul>
                    {votings.map(voting => {
                    return <li><a href="/voting-page">
                      {voting.name} {'<3'}
                    </a></li>
                  })} 
                  </ul>
                  : null
                }
            </div>
          </div>
        </div>
      </div>
      <Footer />
    </div>
  ) : (
    <Redirect
      to={{
        pathname: "/login-page",
      }}
    />
  );
}
