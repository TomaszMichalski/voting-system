import React, { useEffect } from "react";
import { useDispatch, useSelector } from "react-redux";
import { Redirect } from "react-router-dom";

// @material-ui/core components
import { makeStyles } from "@material-ui/core/styles";

// @material-ui/icons

// core components
import Footer from "components/Footer/Footer.js";
import GridContainer from "components/Grid/GridContainer.js";

import Button from "components/CustomButtons/Button.js";
import Menu from "components/Menu/Menu.js";
import Parallax from "components/Parallax/Parallax.js";

import styles from "assets/jss/material-kit-react/views/landingPage.js";

import Radio from "@material-ui/core/Radio";
import RadioGroup from "@material-ui/core/RadioGroup";
import FormControlLabel from "@material-ui/core/FormControlLabel";
import FormControl from "@material-ui/core/FormControl";
import FormLabel from "@material-ui/core/FormLabel";
import FormHelperText from "@material-ui/core/FormHelperText";

import { votingActions } from "redux_elems/_actions/vote.actions";

const useStyles = makeStyles(styles);

const useCustomStyles = makeStyles((theme) => ({
  formControl: {
    margin: theme.spacing(3),
  },
  button: {
    margin: theme.spacing(1, 1, 0, 0),
  },
  votingContainer: {
    backgroundColor: "#fff",
    width: "100%",
    padding: "20px 40px",
  },
}));

export const VotingPage = () => {
  const classes = useStyles();
  const customClasses = useCustomStyles();

  const options = useSelector((state) => state.option.options);
  const dispatch = useDispatch();
  useEffect(() => dispatch(votingActions.getOptions()), []);

  const [value, setValue] = React.useState("");
  const [error, setError] = React.useState(false);
  const [helperText, setHelperText] = React.useState("Choose wisely");

  const handleChange = (event) => {
    setValue(event.target.value);
    setHelperText(" ");
    setError(false);
  };

  const handleSubmit = (event) => {
    event.preventDefault();

    if (value) {
      setHelperText("You got it!");
      setError(false);
      dispatch(votingActions.vote(value));
    } else {
      setHelperText("Please select an option.");
      setError(true);
    }
  };

  return localStorage.getItem("token") ? (
    <div>
      <Menu />
      <Parallax filter image={require("assets/img/voting-bg.png")}>
        <div className={classes.container}>
          <GridContainer>
            <div className={customClasses.votingContainer}>
              <form onSubmit={handleSubmit}>
                <FormControl
                  component="fieldset"
                  error={error}
                  className={customClasses.formControl}
                >
                  <FormLabel component="legend">Election</FormLabel>
                  <RadioGroup
                    aria-label="electionr"
                    name="election1"
                    value={value}
                    onChange={handleChange}
                  >
                    {options
                      ? options.map((val) => (
                          <FormControlLabel
                            value={val}
                            control={<Radio />}
                            label={val}
                          />
                        ))
                      : null}
                  </RadioGroup>
                  <FormHelperText>{helperText}</FormHelperText>
                  <Button
                    type="submit"
                    variant="outlined"
                    color="primary"
                    className={customClasses.button}
                  >
                    Vote
                  </Button>
                </FormControl>
              </form>
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
