import React, { Component } from 'react';
import './App.css';
import Navbar from "./components/Navbar";
import Register from "./components/Register";

class App extends Component {
  render() {
    return (
      <div className="App">
          <Navbar/>
          {/*<Register/>*/}
      </div>
    );
  }
}

export default App;