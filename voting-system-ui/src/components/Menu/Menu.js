import React from "react";
// @material-ui/core components
import { makeStyles } from "@material-ui/core/styles";
import List from "@material-ui/core/List";
import ListItem from "@material-ui/core/ListItem";

// core components
import Header from "components/Header/Header.js";

import Button from "components/CustomButtons/Button.js";

import styles from "assets/jss/material-kit-react/views/componentsSections/navbarsStyle.js";

import logo from "assets/img/logo-white.png";

const useStyles = makeStyles(styles);

const useCustomStyles = makeStyles({
  logo: {
    maxWidth: "100px",
    marginLeft: "-16px",
  },
});

export default function Menu() {
  const classes = useStyles();
  const customClasses = useCustomStyles();
  return (
    <Header
      color="transparent"
      leftLinks={<a href="/"><img src={logo} alt="Logo" className={customClasses.logo} /></a>}
      rightLinks={
        <List className={classes.list}>
          <ListItem className={classes.listItem}>
            <Button
              href="/"
              className={classes.navLink}
              color="transparent"
            >
              Home
            </Button>
          </ListItem>
          <ListItem className={classes.listItem}>
            <Button
              href="/voting-page"
              className={classes.navLink}
              color="transparent"
            >
              Voting System
            </Button>
          </ListItem>
          {
          localStorage.getItem('user')
          ?
          <>
            <ListItem className={classes.listItem}>
              <Button
                href="/profile-page"
                className={classes.navLink}
                color="transparent"
              >
                My Profile
              </Button>
            </ListItem>
            <ListItem className={classes.listItem}>
              <Button
                href="/"
                onClick={() => localStorage.removeItem('user')}
                className={classes.registerNavLink}
                color="danger"
                round
              >
                Logout
              </Button>
            </ListItem>
          </>
          :
          <ListItem className={classes.listItem}>
            <Button
              href="/login-page"
              className={classes.registerNavLink}
              color="info"
              round
            >
              Register
            </Button>
          </ListItem>
          }
        </List>
      }
      fixed
      changeColorOnScroll={{
        height: 400,
        color: "white",
      }}
    />
  );
}
