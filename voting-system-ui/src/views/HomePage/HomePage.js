import React, { useEffect } from "react";
import { connect } from 'react-redux';

import { userActions } from 'redux_elems/_actions/user.actions';

// @material-ui/core components
import { makeStyles } from "@material-ui/core/styles";

// @material-ui/icons

// core components
import Footer from "components/Footer/Footer.js";
import GridContainer from "components/Grid/GridContainer.js";
import GridItem from "components/Grid/GridItem.js";
import Button from "components/CustomButtons/Button.js";
import Menu from "components/Menu/Menu.js";
import Parallax from "components/Parallax/Parallax.js";

import styles from "assets/jss/material-kit-react/views/landingPage.js";

const useStyles = makeStyles(styles);

function HomePage(props) {
  const { dispatch } = props;

  console.log(props);

  const classes = useStyles();
  const { ...rest } = props;
  return (
    <div>
      <Menu />
      <Parallax filter image={require("assets/img/voting-bg.png")}>
        <div className={classes.container}>
          <GridContainer>
            <GridItem xs={12} sm={12} md={6}>
              <h1 className={classes.title}>
          
                Welcome to the online voting system
              </h1>
              <h4>Goverment election | state election | court vote </h4> <br />
              <Button
                color="info"
                size="lg"
                round
                href="/login-page"
                rel="noopener noreferrer"
              >
                Register now
              </Button>
            </GridItem>
          </GridContainer>
        </div>
      </Parallax>

      <Footer />
    </div>
  );
}

function mapStateToProps(state) {
  const { users, authentication } = state;
  const { user } = authentication;
  return {
      user,
      users
  };
}

const connectedHomePage = connect(mapStateToProps)(HomePage);
export { connectedHomePage as HomePage };