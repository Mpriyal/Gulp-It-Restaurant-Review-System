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

class App extends Component {
  render() {
    return (
      <div className="App">
        {/*<Homepage/>*/}
          <Navbar/>
          <SearchHome/>
          {/*<Register/>*/}
          {/*<ProfileCustomer/>*/}
          {/*<MoreinfoCustomer/>*/}
          {/*<OwnerProfile/>*/}
          {/*<MoreinfoOwner/>*/}
          {/*<RestaurantList/>*/}
      </div>
    );
  }
}

export default App;