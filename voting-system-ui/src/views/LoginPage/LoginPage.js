import React, { useState } from "react";
// @material-ui/core components
import { makeStyles } from "@material-ui/core/styles";
import InputAdornment from "@material-ui/core/InputAdornment";
import Icon from "@material-ui/core/Icon";
// @material-ui/icons
import Email from "@material-ui/icons/Email";
import People from "@material-ui/icons/People";
import LockOpen from "@material-ui/icons/LockOpen";
import AssignmentInd from "@material-ui/icons/AssignmentInd";
import PersonAdd from "@material-ui/icons/PersonAdd";

// core components
import Menu from "components/Menu/Menu.js";
import Footer from "components/Footer/Footer.js";
import GridContainer from "components/Grid/GridContainer.js";
import GridItem from "components/Grid/GridItem.js";
import Button from "components/CustomButtons/Button.js";
import Card from "components/Card/Card.js";
import CardBody from "components/Card/CardBody.js";
import CardHeader from "components/Card/CardHeader.js";
import CardFooter from "components/Card/CardFooter.js";
import CustomInput from "components/CustomInput/CustomInput.js";
import CustomTabs from "components/CustomTabs/CustomTabs.js";

import styles from "assets/jss/material-kit-react/views/loginPage.js";

import image from "assets/img/voting-bg.png";

import { connect } from "react-redux";

import { userActions } from "redux_elems/_actions/user.actions";

const useStyles = makeStyles(styles);

const mockLogin = () => {
  alert("Functionality not available yet!");
};

function LoginPage(props) {
  const [cardAnimaton, setCardAnimation] = React.useState("cardHidden");
  setTimeout(function() {
    setCardAnimation("");
  }, 700);

  console.log(props);

  const classes = useStyles();
  const { loggingIn, dispatch } = props;

  const [name, setName] = useState("");
  const [lastName, setLastName] = useState("");
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");

  const handleLoginSubmit = (e) => {
    e.preventDefault();

    dispatch(userActions.login(name, password));
  };

  return (
    <div>
      <Menu />
      <div
        className={classes.pageHeader}
        style={{
          backgroundImage: "url(" + image + ")",
          backgroundSize: "cover",
          backgroundPosition: "top center",
        }}
      >
        <div className={classes.container}>
          <GridContainer justify="center">
            <GridItem xs={12} sm={12} md={4}>
              <Card className={classes[cardAnimaton]}>
                <CardHeader color="primary" className={classes.cardHeader}>
                  <h4>Create Account or log into already existing account</h4>
                  <div className={classes.socialLine}>
                    <Button
                      justIcon
                      target="_blank"
                      color="transparent"
                      onClick={(e) => mockLogin(e)}
                    >
                      <i className={"fab fa-twitter"} />
                    </Button>
                    <Button
                      justIcon
                      target="_blank"
                      color="transparent"
                      onClick={(e) => mockLogin(e)}
                    >
                      <i className={"fab fa-facebook"} />
                    </Button>
                    <Button
                      justIcon
                      target="_blank"
                      color="transparent"
                      onClick={(e) => mockLogin(e)}
                    >
                      <i className={"fab fa-google-plus-g"} />
                    </Button>
                  </div>
                </CardHeader>
                <CustomTabs
                  headerColor="primary"
                  tabs={[
                    {
                      tabName: "Register",
                      tabIcon: PersonAdd,
                      tabContent: (
                        <form className={classes.form} onSubmit={mockLogin}>
                          <CardBody>
                            <CustomInput
                              labelText="First Name..."
                              id="first"
                              formControlProps={{
                                fullWidth: true,
                              }}
                              inputProps={{
                                type: "text",
                                value: name,
                                onChange: (e) => setName(e.target.value),
                                endAdornment: (
                                  <InputAdornment position="end">
                                    <People
                                      className={classes.inputIconsColor}
                                    />
                                  </InputAdornment>
                                ),
                              }}
                            />
                            <CustomInput
                              labelText="Last Name"
                              id="last"
                              formControlProps={{
                                fullWidth: true,
                              }}
                              inputProps={{
                                type: "text",
                                value: lastName,
                                onChange: (e) => setLastName(e.target.value),
                                endAdornment: (
                                  <InputAdornment position="end">
                                    <AssignmentInd
                                      className={classes.inputIconsColor}
                                    />
                                  </InputAdornment>
                                ),
                              }}
                            />
                            <CustomInput
                              labelText="Email..."
                              id="email"
                              formControlProps={{
                                fullWidth: true,
                              }}
                              inputProps={{
                                type: "email",
                                value: email,
                                onChange: (e) => setEmail(e.target.value),
                                endAdornment: (
                                  <InputAdornment position="end">
                                    <Email
                                      className={classes.inputIconsColor}
                                    />
                                  </InputAdornment>
                                ),
                              }}
                            />
                            <CustomInput
                              labelText="Password"
                              id="pass"
                              formControlProps={{
                                fullWidth: true,
                              }}
                              inputProps={{
                                type: "password",
                                value: password,
                                onChange: (e) => setPassword(e.target.value),
                                endAdornment: (
                                  <InputAdornment position="end">
                                    <Icon className={classes.inputIconsColor}>
                                      lock_outline
                                    </Icon>
                                  </InputAdornment>
                                ),
                                autoComplete: "off",
                              }}
                            />
                          </CardBody>
                          <CardFooter className={classes.cardFooter}>
                            <Button
                              simple
                              color="primary"
                              size="lg"
                              type="submit"
                            >
                              Register
                            </Button>
                          </CardFooter>
                        </form>
                      ),
                    },
                    {
                      tabName: "Login",
                      tabIcon: LockOpen,
                      tabContent: (
                        <form
                          className={classes.form}
                          onSubmit={handleLoginSubmit}
                        >
                          <CardBody>
                            <CustomInput
                              labelText="First Name..."
                              id="first"
                              formControlProps={{
                                fullWidth: true,
                              }}
                              inputProps={{
                                type: "text",
                                value: name,
                                onChange: (e) => setName(e.target.value),
                                endAdornment: (
                                  <InputAdornment position="end">
                                    <People
                                      className={classes.inputIconsColor}
                                    />
                                  </InputAdornment>
                                ),
                              }}
                            />
                            <CustomInput
                              labelText="Password"
                              id="pass"
                              formControlProps={{
                                fullWidth: true,
                              }}
                              inputProps={{
                                type: "password",
                                value: password,
                                onChange: (e) => setPassword(e.target.value),
                                endAdornment: (
                                  <InputAdornment position="end">
                                    <Icon className={classes.inputIconsColor}>
                                      lock_outline
                                    </Icon>
                                  </InputAdornment>
                                ),
                                autoComplete: "off",
                              }}
                            />
                          </CardBody>
                          <CardFooter className={classes.cardFooter}>
                            <Button
                              simple
                              color="primary"
                              size="lg"
                              type="submit"
                            >
                              Login
                            </Button>
                          </CardFooter>
                        </form>
                      ),
                    },
                  ]}
                />
              </Card>
            </GridItem>
          </GridContainer>
        </div>
        <Footer whiteFont />
      </div>
    </div>
  );
}

function mapStateToProps(state) {
  const { loggingIn } = state.authentication;
  return {
    loggingIn,
  };
}

const connectedLoginPage = connect(mapStateToProps)(LoginPage);
export { connectedLoginPage as LoginPage };
