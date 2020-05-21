import React from "react";
import { useDispatch } from "react-redux";

import { votingActions } from "redux_elems/_actions/vote.actions";

import Button from "components/CustomButtons/Button.js";
import Radio from "@material-ui/core/Radio";
import RadioGroup from "@material-ui/core/RadioGroup";
import FormControl from "@material-ui/core/FormControl";
import FormLabel from "@material-ui/core/FormLabel";
import FormHelperText from "@material-ui/core/FormHelperText";
import FormControlLabel from "@material-ui/core/FormControlLabel";

export const SingleChoice = (props) => {
  const dispatch = useDispatch();

  const { id, votingsName, options } = props;
  const handleChange = (event) => {
    setValue(event.target.value);
    setHelperText(" ");
    setError(false);
  };

  const [value, setValue] = React.useState("");
  const [error, setError] = React.useState(false);
  const [helperText, setHelperText] = React.useState("Choose wisely");

  const handleSubmit = (event) => {
    event.preventDefault();

    if (value) {
      const selectedOption = options.filter(option => option.name === value);
      setHelperText("You got it!");
      setError(false);
      dispatch(votingActions.vote(id, [selectedOption[0].id]));
    } else {
      setHelperText("Please select an option.");
      setError(true);
    }
  };

  return (
    <form onSubmit={handleSubmit}>
      <FormControl
        component="fieldset"
        error={error}
      >
        <FormLabel component="legend">{votingsName}</FormLabel>
        <RadioGroup
          aria-label="electionr"
          name="election1"
          value={value}
          onChange={handleChange}
        >
          {options.map((val) => (
            <FormControlLabel
              key={val.id}
              value={val.name}
              control={<Radio />}
              label={val.name}
            />
          ))}
        </RadioGroup>
        <FormHelperText>{helperText}</FormHelperText>
        <Button
          type="submit"
          variant="outlined"
          color="primary"
        >
          Vote
        </Button>
      </FormControl>
    </form>
  );
};
