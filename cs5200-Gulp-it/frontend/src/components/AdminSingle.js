import React from 'react'

export default class AdminSingle extends React.Component{
  constructor(props) {
    super(props);
  }
  render(){
    return(
      <div className={"container p-5 m-5"}>
          <div className="row">
                  <div className="col-sm-4" key={this.props.owner.id}>
                      <div className="card">
                          <div className="card-body">
                              <h5 className="card-title">{this.props.owner.firstName}{this.props.owner.lastName} </h5>
                              <p className="card-text">id: {this.props.owner.id}</p>
                              <p className="card-text">UserName: {this.props.owner.username}</p>
                              <p className="card-text">Password: {this.props.owner.password}</p>
                              <p className="card-text">Email: {this.props.owner.email}</p>
                              <p className="card-text">Type: {this.props.owner.type}</p>
                              <p className="card-text">Key: {this.props.owner.ownerKey}</p>
                              <p className="card-text">Date of Birth: {this.props.owner.dob}</p>
                          </div>
                      </div>
                  </div>
              )
          </div>

      </div>
    )
}
}
