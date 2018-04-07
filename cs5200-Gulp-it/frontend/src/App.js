import React, { Component } from 'react';

import './App.css';
import SearchHome from './components/SearchHome';
import Navbar from "./components/Navbar";

class App extends Component {
  render() {
    return (
      <div className="App">
          <Navbar/>
          <SearchHome/>
      </div>
    );
  }
}

export default App;