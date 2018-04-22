import React from 'react'
import OwnerProfile from './OwnerProfile'
import SearchHome from './SearchHome'
import { render } from 'react-dom'
import { BrowserRouter } from 'react-router-dom'
import { Switch, Route } from 'react-router-dom'
import ProfileCustomer from './ProfileCustomer'
import Register from './Register'
import AdminCustomer from './AdminCustomer'
import Admin from './Admin'
import AdminOwner from './AdminOwner'
import AdminRestaurant from './AdminRestaurant'
import AdminMenu from './AdminMenu'





const Main = () => (
  <main>
    <Switch>
      <Route exact  path='/' component={SearchHome}/>
      <Route exact path='/profile' component={ProfileCustomer}/>
      <Route exact path='/owner' component={OwnerProfile}/>
      <Route exact path='/registeration' component={Register}/>
      <Route exact path='/admin' component={Admin}/>
      <Route exact path='/admin/owner' component={AdminOwner}/>
      <Route exact path='/admin/customer' component={AdminCustomer}/>
      <Route exact path='/admin/restaurant' component={AdminRestaurant}/>
      <Route exact path='/admin/menu' component={AdminMenu}/>
    </Switch>
  </main>
)

export default Main;
