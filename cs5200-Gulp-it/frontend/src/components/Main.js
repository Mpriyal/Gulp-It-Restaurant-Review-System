import React from 'react'
import OwnerProfile from './OwnerProfile'
import SearchHome from './SearchHome'
import { render } from 'react-dom'
import { BrowserRouter } from 'react-router-dom'
import { Switch, Route } from 'react-router-dom'
import ProfileCustomer from './ProfileCustomer'
import Register from './Register'
import AdminCustomer from './AdminCustomer'


const Main = () => (
  <main>
    <Switch>
      <Route exact  path='/' component={SearchHome}/>
      <Route exact path='/profile' component={ProfileCustomer}/>
      <Route exact path='/owner' component={OwnerProfile}/>
      <Route exact path='/registeration' component={Register}/>
      <Route exact path='/admin' component={AdminCustomer}/>

    </Switch>
  </main>
)

export default Main;
