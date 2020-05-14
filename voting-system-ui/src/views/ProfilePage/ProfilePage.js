import React from "react";
// nodejs library that concatenates classes
import classNames from "classnames";
// @material-ui/core components
import { makeStyles } from "@material-ui/core/styles";
import { Redirect } from "react-router-dom";

// core components;
import Footer from "components/Footer/Footer.js";
import GridContainer from "components/Grid/GridContainer.js";
import GridItem from "components/Grid/GridItem.js";
import Menu from "components/Menu/Menu.js";
import Parallax from "components/Parallax/Parallax.js";

import avatar from "assets/img/avatar.jpg";
import styles from "assets/jss/material-kit-react/views/profilePage.js";

const useStyles = makeStyles(styles);

export default function ProfilePage(props) {
  const classes = useStyles();
  const imageClasses = classNames(
    classes.imgRaised,
    classes.imgRoundedCircle,
    classes.imgFluid
  );

  const user = JSON.parse(localStorage.getItem("user"));
  return user ? (
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
                    <h3 className={classes.title}>{`Welcome ${user.firstName} ${user.lastName}!`}</h3>
                    <p>{`Nickname: ${user.username}`}</p>
                    <p>{`First name: ${user.firstName}`}</p>
                    <p>{`Last name: ${user.lastName}`}</p>
                  </div>
                </div>
              </GridItem>
            </GridContainer>
            <div className={classes.description}>
              <h3>See your voting:</h3>

                  <a href="/voting-page">
                    Presidental Election
                  </a>
  
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
