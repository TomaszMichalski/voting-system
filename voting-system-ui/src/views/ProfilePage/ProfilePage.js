import React, { useEffect } from "react";
import { useDispatch, useSelector } from "react-redux";
// nodejs library that concatenates classes
import classNames from "classnames";
// @material-ui/core components
import { makeStyles } from "@material-ui/core/styles";
import { Redirect } from "react-router-dom";

import { votingActions } from "redux_elems/_actions/vote.actions.js";
import { userActions } from "redux_elems/_actions/user.actions.js";

// core components;
import Footer from "components/Footer/Footer.js";
import GridContainer from "components/Grid/GridContainer.js";
import GridItem from "components/Grid/GridItem.js";
import Menu from "components/Menu/Menu.js";
import Parallax from "components/Parallax/Parallax.js";
import Button from "components/CustomButtons/Button.js";

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

  const dispatch = useDispatch();
  useEffect(() => {
    dispatch(votingActions.getVotings());
    dispatch(userActions.getUser());
  }, []);

  const user = useSelector((state) => state.getUser.user);
  const votings = useSelector((state) => state.votings.votings);

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
                    <h3 className={classes.title}>{`Welcome ${
                      user ? user.name : ""
                    }!`}</h3>
                    <p>{`Name: ${user ? user.name : ""}`}</p>
                    <p>{`Email: ${user ? user.email : ""}`}</p>
                  </div>
                </div>
              </GridItem>
            </GridContainer>
            <div className={classes.description}>
              <h3>See your voting:</h3>
              {votings ? (
                <ul style={{ listStyleType: "none", padding: 0 }}>
                  {votings.map((voting) => {
                    return (
                      <li key={voting.id}>
                        <GridContainer justify="center">
                          <GridItem xs={12} sm={12} md={3}>
                            <h4>{voting.name}</h4>
                          </GridItem>
                          <GridItem xs={12} sm={12} md={2}>
                            <Button
                              href={`/voting-page/${voting.id}`}
                              color="info"
                              disabled={!voting.isActive}
                            >
                              Vote
                            </Button>
                          </GridItem>
                          <GridItem xs={12} sm={12} md={3}>
                            <Button
                              href={`/result-page/${voting.id}`}
                              color="info"
                              disabled={!voting.isExpired}
                            >
                              See results
                            </Button>
                          </GridItem>
                        </GridContainer>
                      </li>
                    );
                  })}
                </ul>
              ) : null}
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
};
