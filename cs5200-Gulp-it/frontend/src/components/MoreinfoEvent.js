import React from 'react'
import axios from 'axios';


export default class MoreinfoEvent extends React.Component {
  constructor(props) {
    super(props);
    this.props={

    }

    render(){
      return(
        <div className="p-5 col-8">
        <p className="head">
        My Event List:
        </p>
        <table className="table table-dark m-t-5">
            <thead>
              <tr>
                <th scope="col">Id</th>
                <th scope="col">Name</th>
                <th scope="col">Date</th>
                <th scope="col">Description</th>

              </tr>
            </thead>
            <tbody>

        {
          this.state.events.map((event,index)=>
          <tr key={index}>
          <th scope="row">{event.id}</th>
          <td>{event.event_name}</td>
          <td>{event.date}</td>
          <td>{event.description}</td>
          <td><button className="btn btn-danger btn-sm" onClick={this.handleDeleteevent.bind(this,event.id,index)}> Delete</button></td>
          <td><button className="btn btn-success btm-sm"onClick={this.handleMoreinfoevent.bind(this,event.id,index)}> MoreInfo</button></td>
          </tr>
        )
        }
            </tbody>
          </table>
          </div>
      )
    }

  }
