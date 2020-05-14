import React, { useEffect } from "react";
import { useDispatch, useSelector } from "react-redux";
import { votingActions } from "redux_elems/_actions/vote.actions";
import { Redirect } from 'react-router-dom';
import { Chart } from "./sections/Chart";

// @material-ui/core components
import { makeStyles } from "@material-ui/core/styles";

// @material-ui/icons

// core components
import Footer from "components/Footer/Footer.js";
import GridContainer from "components/Grid/GridContainer.js";

import Menu from "components/Menu/Menu.js";
import Parallax from "components/Parallax/Parallax.js";

import styles from "assets/jss/material-kit-react/views/landingPage.js";

const useStyles = makeStyles(styles);

const useCustomStyles = makeStyles((theme) => ({
  resultContainer: {
    backgroundColor: "#fff",
    width: "100%",
    padding: "20px 40px",
  },
  title: {
    color: "#000",
    marginBottom: "40px",
  },
}));

const prepareData = (resultList) => {
  let data = [];

  Object.entries(resultList[0]).forEach(([k, v]) => {
    const obj = { label: k, y: v };
    data.push(obj);
  });
  return data;
};

export const ResultPage = () => {
  const classes = useStyles();
  const customClasses = useCustomStyles();

  const results = useSelector((state) => state.result.results);
  const dispatch = useDispatch();
  useEffect(() => dispatch(votingActions.getResults()), []);

  return localStorage.getItem("user") ? (
    <div>
      <Menu />
      <Parallax filter image={require("assets/img/voting-bg.png")}>
        <div className={classes.container}>
          <GridContainer>
            <div className={customClasses.resultContainer}>
              <h1 className={customClasses.title}>Exit Poll Results</h1>
              {results ? (
                <Chart
                  title="Presidental election"
                  chartData={prepareData(results)}
                />
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
