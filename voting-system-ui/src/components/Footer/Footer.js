/*eslint-disable*/
import React from "react";
// nodejs library to set properties for components
import PropTypes from "prop-types";
// nodejs library that concatenates classes
import classNames from "classnames";
// material-ui core components
import { List, ListItem } from "@material-ui/core";
import { makeStyles } from "@material-ui/core/styles";

// @material-ui/icons
import Favorite from "@material-ui/icons/Favorite";

import styles from "assets/jss/material-kit-react/components/footerStyle.js";

const useStyles = makeStyles(styles);

export default function Footer(props) {
  const classes = useStyles();
  const { whiteFont } = props;
  const footerClasses = classNames({
    [classes.footer]: true,
    [classes.footerWhiteFont]: whiteFont,
  });
  const aClasses = classNames({
    [classes.a]: true,
    [classes.footerWhiteFont]: whiteFont,
  });
  return (
    <footer className={footerClasses}>
      <div className={classes.container}>
        <div className={classes.left}>
          <List className={classes.list}>
            <ListItem className={classes.inlineBlock}>
              <a
                href="/"
                className={classes.block}
              >
                Home
              </a>
            </ListItem>
            <ListItem className={classes.inlineBlock}>
              <a
                href="/login-page"
                className={classes.block}
              >
                Registration
              </a>
            </ListItem>
          </List>
        </div>
        <div className={classes.right}>
          &copy; {1900 + new Date().getYear()} {" "}
          created by <a href="https://github.com/Andrzej97">Andrzej97</a> {" "} <a href="https://github.com/Codetype">Codetype</a> {" "} 
           <a href="https://github.com/lukkulig">lukkulig</a> {" "} <a href="https://github.com/TomaszMichalski/">TomaszMichalski</a> 
        </div>
      </div>
    </footer>
  );
}

Footer.propTypes = {
  whiteFont: PropTypes.bool,
};
