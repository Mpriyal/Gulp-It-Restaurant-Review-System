import React from 'react'
import { Link } from 'react-router-dom'

export default class Admin extends React.Component{
  constructor(props){
    super(props);
  }

  render(){
    return(
      <div className="m-3">
      <p className="head"> Hello Admin </p>
      <div className="row">
        <div className="col-12 m-2">
            <button className="btn btn-primary">
            <Link style={{display: 'block', height: '100%',color:'white'}} to='/admin/owner'>Owner Panel</Link>
            </button>
        </div>
        <div className="col-12 m-2">
            <button className="btn btn-primary">
            <Link style={{display: 'block', height: '100%',color:'white'}} to='/admin/customer'>Customer Panel</Link>
            </button>
        </div>
      </div>
      <div className="row">
        <div className="col-12 m-2">
            <button className="btn btn-primary">
            <Link style={{display: 'block', height: '100%',color:'white'}} to='/admin/restaurant'>Restaurant Panel</Link>
            </button>
        </div>
        <div className="col-12 m-2">
            <button className="btn btn-primary">
            <Link style={{display: 'block', height: '100%',color:'white'}} to='/admin/menu'>Menu Panel</Link>
            </button>
        </div>
      </div>
      </div>
    )
  }
}
