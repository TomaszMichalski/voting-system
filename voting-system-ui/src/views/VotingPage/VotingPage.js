import React, { useEffect } from "react";
import { useDispatch, useSelector } from "react-redux";
import { Redirect } from "react-router-dom";

// @material-ui/core components
import { makeStyles } from "@material-ui/core/styles";

// @material-ui/icons

// core components
import Footer from "components/Footer/Footer.js";
import GridContainer from "components/Grid/GridContainer.js";

import Menu from "components/Menu/Menu.js";
import Parallax from "components/Parallax/Parallax.js";
import { SingleChoice } from "./Sections/SingleChoice.js";
import { MultiChoice } from "./Sections/MultiChoice.js";

import styles from "assets/jss/material-kit-react/views/landingPage.js";

import { votingActions } from "redux_elems/_actions/vote.actions";

const useStyles = makeStyles(styles);

const useCustomStyles = makeStyles((theme) => ({
  votingContainer: {
    backgroundColor: "#fff",
    width: "100%",
    padding: "20px 40px",
  },
}));

export const VotingPage = (props) => {
  const classes = useStyles();
  const customClasses = useCustomStyles();

  const { match } = props;
  const { id } = match.params;

  const options = useSelector((state) => state.option.options);
  const dispatch = useDispatch();
  useEffect(() => dispatch(votingActions.getOptions(id)), []);

  return localStorage.getItem("token") ? (
    <div>
      <Menu />
      <Parallax filter image={require("assets/img/voting-bg.png")}>
        <div className={classes.container}>
          <GridContainer>
            <div className={customClasses.votingContainer}>
              {options ? (
                options.singleChoice ? (
                  <SingleChoice
                    votingsName={options.name}
                    options={options.options}
                    id={id}
                  />
                ) : (
                  <MultiChoice
                    votingsName={options.name}
                    options={options.options}
                    id={id}
                  />
                )
              ) : null}
            </div>
          </GridContainer>
        </div>
      </Parallax>
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
