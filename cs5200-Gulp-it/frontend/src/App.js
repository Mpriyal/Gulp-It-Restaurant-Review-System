import React, { Component } from 'react';
import './App.css';
import Navbar from "./components/Navbar";
import Register from "./components/Register";
import Homepage from "./components/Homepage";
import ProfileCustomer from "./components/ProfileCustomer";
import MoreinfoCustomer from "./components/MoreinfoCustomer";
import OwnerProfile from "./components/OwnerProfile";
import MoreinfoOwner from "./components/MoreInfoOwner";
import SearchHome from "./components/SearchHome";
import RestaurantList from "./components/RestaurantList";
import Main from "./components/Main"
import AdminCustomer from './components/AdminCustomer'
import AdminOwner from './components/AdminOwner'
import OwnerCard from './components/OwnerCard'
import Admin from './components/Admin'
import AdminRestaurant from './components/AdminRestaurant'
import AdminMenu from './components/AdminMenu'


class App extends Component {
  render() {
    return (
      <div className="App">
          <Navbar/>
      <Main/>
      </div>
    );
  }
}

export default App;
