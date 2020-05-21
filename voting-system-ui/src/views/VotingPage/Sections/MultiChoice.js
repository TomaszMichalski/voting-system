import React from "react";
import { useDispatch } from "react-redux";

import { votingActions } from "redux_elems/_actions/vote.actions";

import Button from "components/CustomButtons/Button.js";
import Checkbox from "@material-ui/core/Checkbox";
import FormControl from "@material-ui/core/FormControl";
import FormLabel from "@material-ui/core/FormLabel";
import FormHelperText from "@material-ui/core/FormHelperText";
import FormControlLabel from "@material-ui/core/FormControlLabel";
import FormGroup from '@material-ui/core/FormGroup';

export const MultiChoice = (props) => {
  const dispatch = useDispatch();

  const { id, votingsName, options } = props;
  const [optionsDict, setOptionsDict] = React.useState({});
  const [error, setError] = React.useState(false);
  const [helperText, setHelperText] = React.useState("Choose wisely");

  const handleSubmit = (event) => {
    event.preventDefault();

    
    const selectedOptions = options.filter(option => optionsDict[option.name] === false)
                                   .map(option => option.id)

    if (selectedOptions.length) {
      setHelperText("You got it!");
      setError(false);
      dispatch(votingActions.vote(id, selectedOptions));
    } else {
      setHelperText("Please select an option.");
      setError(true);
    }
  };


  const handleChange = (event) => {
    const tmp = optionsDict;
    tmp[event.target.name] = !event.target.checked;
    setOptionsDict(tmp);
  };

  return (
    <form onSubmit={handleSubmit}>
      <FormControl component="fieldset" error={error}>
        <FormLabel component="legend">{votingsName}</FormLabel>
        <FormGroup>
          {options.map((val) => (
            <FormControlLabel
              key={val.id}
              control={
                <Checkbox
                  checked={optionsDict[val.name]}
                  onChange={handleChange}
                  name={val.name}
                />
              }
              label={val.name}
            />
          ))}
        </FormGroup>
        <FormHelperText>{helperText}</FormHelperText>
        <Button type="submit" variant="outlined" color="primary">
          Vote
        </Button>
      </FormControl>
    </form>
  );
};
