import React from 'react'

export default class AdminSingle extends React.Component{
  constructor(props) {
    super(props);
  }
  render(){
    console.log(this);
    return(
      <div className={"container p-5 m-5"}>
          <div className="row">
                  <div className="col-sm-4" key={this.props.data.id}>
                      <div className="card">
                          <div className="card-body">
                              <h5 className="card-title">{this.props.data.firstName}{this.props.data.lastName} </h5>
                              <p className="card-text">id: {this.props.data.id}</p>
                              <p className="card-text">UserName: {this.props.data.username}</p>
                              <p className="card-text">Password: {this.props.data.password}</p>
                              <p className="card-text">Email: {this.props.data.email}</p>
                              <p className="card-text">Type: {this.props.data.type}</p>
                              <p className="card-text">Key: {this.props.data.ownerKey}</p>
                              <p className="card-text">Date of Birth: {this.props.data.dob}</p>
                          </div>
                      </div>
                  </div>
              
          </div>

      </div>
    )
}
}
