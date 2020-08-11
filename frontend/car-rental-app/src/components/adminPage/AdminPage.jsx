import React from "react";
import { Route, Switch, HashRouter } from "react-router-dom";

import UserNavbar from "./adminNavbar/AdminNavbar";
import AddNewCar from "./manageCars/addNewCar/AddNewCar";
import RemoveCar from "./manageCars/removeCar/RemoveCar";
import AddEmployee from "./manageEmployees/addEmployee/AddEmployee";
import RemoveEmployer from "./manageEmployees/removeEmployer/RemoveEmployer";
import RentRequests from "./rentRequests/RentRequests";
import Footer from "../footer/Footer";

const AdminPage = () => {
  return (
    <div>
      <UserNavbar />
      <div>
        <HashRouter basename="/adminPage">
          <Switch>
            <Route path="/" exact component={AddNewCar} />
            <Route path="/removeCar" component={RemoveCar} />
            <Route path="/addEmployee" component={AddEmployee} />
            <Route path="/removeEmployer" component={RemoveEmployer} />
            <Route path="/rentRequest" component={RentRequests} />
          </Switch>
        </HashRouter>
      </div>
      <div>
        <Footer />
      </div>
    </div>
  );
};

export default AdminPage;
