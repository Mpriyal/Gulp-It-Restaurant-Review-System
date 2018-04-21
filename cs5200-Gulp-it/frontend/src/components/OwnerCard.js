import React from 'react'

export default class OwnerCard extends React.Component {
  constructor(props) {
    super(props);
  }
  render(){
    return(
      <div className={"container p-5 m-5"}>
          <div className="row">
              {this.props.data.map((owner,index) =>
                  <div className="col-sm-4" key={owner.id}>
                      <div className="card">
                          <div className="card-body">
                              <h5 className="card-title">{owner.firstName}{owner.lastName} </h5>
                              <p className="card-text">id: {owner.id}</p>
                              <p className="card-text">UserName: {owner.username}</p>
                              <p className="card-text">Password: {owner.password}</p>
                              <p className="card-text">Email: {owner.email}</p>
                              <p className="card-text">Type: {owner.type}</p>
                              <p className="card-text">Key: {owner.ownerKey}</p>
                              <p className="card-text">Date of Birth: {owner.dob}</p>
                          </div>
                      </div>
                  </div>
              )}
          </div>

      </div>
    )
  }

}
