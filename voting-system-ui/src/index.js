import React from "react";
import ReactDOM from "react-dom";
import { Provider } from "react-redux";
import { store } from "redux_elems/_helpers/store";
import { App } from "./App/App";

import "assets/scss/material-kit-react.scss?v=1.8.0";

ReactDOM.render(
  <Provider store={store}>
    <App />
  </Provider>,
  document.getElementById("root")
);
