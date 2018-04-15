import React, { Component } from 'react';
import './App.css';
import Navbar from "./components/Navbar";
import Register from "./components/Register";
import Homepage from "./components/Homepage";
import ProfileCustomer from "./components/ProfileCustomer";
import Moreinfo from "./components/Moreinfo";

class App extends Component {
  render() {
    return (
      <div className="App">
        {/*<Homepage/>*/}
          {/*<Navbar/>*/}
          {/*<Register/>*/}
          {/*<ProfileCustomer/>*/}
          <Moreinfo/>
      </div>
    );
  }
}

export default App;