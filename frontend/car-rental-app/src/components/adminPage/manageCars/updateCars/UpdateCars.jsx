import React, { useState } from "react";
import { useDispatch } from "react-redux";
import { Paper } from "@material-ui/core";
import { makeStyles } from "@material-ui/core";
import { ValidatorForm } from "react-material-ui-form-validator";
import UpdateCarsForm from "./UpdateCarsForm";
import { updateCar } from "../../../../features/car-reservation/reservationSlice";

const useStyles = makeStyles({
  root: {
    padding: "8px",
    width: "50vw",
  },
});

const UpdateCars = ({ car }) => {
  const classes = useStyles();
  const dispatch = useDispatch();
  const exLicensePlate = car.licensePlate;
  const [licensePlate, setLicensePlate] = useState(car.licensePlate);
  const [mileage, setMileage] = useState(car.mileage);
  const [lastInspection, setLastInspection] = useState(
    car.lastInspection.slice(0, 10)
  );

  return (
    <Paper className={classes.root}>
      <ValidatorForm
        onSubmit={(event) => {
          event.preventDefault();

          var newCar = {
            ...car,
            licensePlate: licensePlate,
            mileage: mileage,
            lastInspection: `${lastInspection}T00:00:00`,
          };
          console.log(newCar);
          dispatch(updateCar(exLicensePlate, newCar));
        }}
      >
        <UpdateCarsForm
          licensePlate={licensePlate}
          mileage={mileage}
          lastInspection={lastInspection}
          licensePlateChange={setLicensePlate}
          mileageChange={setMileage}
          lastInspectionChange={setLastInspection}
        />
      </ValidatorForm>
    </Paper>
  );
};

export default UpdateCars;
