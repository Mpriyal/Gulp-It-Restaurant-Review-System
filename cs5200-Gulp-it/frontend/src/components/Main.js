import React from 'react'
import OwnerProfile from './OwnerProfile'
import SearchHome from './SearchHome'
import { render } from 'react-dom'
import { BrowserRouter } from 'react-router-dom'
import { Switch, Route } from 'react-router-dom'
import ProfileCustomer from './ProfileCustomer'

const Main = () => (
  <main>
    <Switch>
      <Route exact  path='/' component={SearchHome}/>
      <Route path='/profile' component={ProfileCustomer}/>
      <Route path='/owner' component={OwnerProfile}/>

    </Switch>
  </main>
)

export default Main;
