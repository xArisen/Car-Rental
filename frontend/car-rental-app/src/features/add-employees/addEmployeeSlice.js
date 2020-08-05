import { createSlice } from "@reduxjs/toolkit";
import axios from "axios";

const API_URL = "http://localhost:8080";

const initialState = {
  firstname: "",
  lastname: "",
  email: "",
  login: "",
  phoneNumber: "",
  password: "",
  rePassword: "",
  didSubmit: false,
  success: false,
  users: [],
};

export const addEmployeeSlice = createSlice({
  name: "addEmployee",
  initialState,
  reducers: {
    firstnameChange: (state, action) => {
      state.firstname = action.payload;
    },
    lastnameChange: (state, action) => {
      state.lastname = action.payload;
    },
    emailChange: (state, action) => {
      state.email = action.payload;
    },
    loginChange: (state, action) => {
      state.login = action.payload;
    },
    phoneNumberChange: (state, action) => {
      state.phoneNumber = action.payload;
    },
    passwordChange: (state, action) => {
      state.password = action.payload;
    },
    rePasswordChange: (state, action) => {
      state.rePassword = action.payload;
    },
    toggleDidSubmit: (state, action) => {
      state.didSubmit = action.payload;
    },
    toggleSuccess: (state, action) => {
      state.success = action.payload;
    },
    reset: (state) => {
      state.firstname = "";
      state.lastname = "";
      state.email = "";
      state.login = "";
      state.phoneNumber = "";
      state.password = "";
      state.rePassword = "";
      state.didSubmit = false;
      state.success = false;
    },

    setUsers: (state, action) => {
      state.users = action.payload;
    },
  },
});

export const {
  firstnameChange,
  lastnameChange,
  emailChange,
  loginChange,
  phoneNumberChange,
  passwordChange,
  rePasswordChange,
  toggleDidSubmit,
  toggleSuccess,
  reset,
  setUsers,
} = addEmployeeSlice.actions;

export const selectAll = (state) => state.addEmployee;

export const selectAllUsers = (state) => state.addEmployee.users;

export const fetchAllUsers = () => async (dispatch) => {
  try {
    const response = await axios.get(API_URL + "/a/users", {
      headers: {
        Authorization: "Bearer " + localStorage.getItem("token"),
      },
    });
    console.log(response.data);
    dispatch(setUsers(response.data));
  } catch (error) {
    console.log(error);
  }
};

export default addEmployeeSlice.reducer;