import React from "react";
import Snackbar from "@material-ui/core/Snackbar";
import MuiAlert from "@material-ui/lab/Alert";

function Alert(props) {
  return <MuiAlert elevation={6} variant="filled" {...props} />;
}

export default function CustomizedSnackbars(props) {
  return (

      <Snackbar
        anchorOrigin={{  vertical: 'top', horizontal: 'center' }}
        open={props.open}
        onClose={props.handleClose}
        autoHideDuration={5000}
      >
        <Alert onClose={props.handleClose} severity={props.severity}>
          {props.message}
        </Alert>
      </Snackbar>

  );
}
