import React from 'react'
import axios from 'axios';


export default class AdminOwner extends React.Component {
  constructor(props) {
    super(props);
    this.state={
      firstName: null,
      lastName: null,
      username: null,
      email:null,
      password:null,
      dateOfBirth: null,
      role:null,
      successMessage:null,
      deleteId:null,
      findById:null
    }
  }

  // posting new Owners

  handleClick(e) {
      e.preventDefault();
      console.log("Success from RegisterPage!")
      fetch('http://localhost:8080/api/customer', {
          method: 'POST',
          headers: {
              'Accept': 'application/json',
              'Content-Type': 'application/json',
          },
          body: JSON.stringify({
              firstName: this.state.firstName,
              lastName: this.state.lastName,
              username:  this.state.username,
              password:  this.state.password,
              email:  this.state.email,
              dob: this.state.dateOfBirth,
              customerKey: "abc"
          })
      }).then(console.log("saved to the db"));
  }

  update(){
      console.log(this);
      this.setState({
          firstName: this.refs.FirstName.value,
          lastName: this.refs.LastName.value,
          username: this.refs.Username.value,
          email:this.refs.Email.value,
          password:this.refs.Password.value,
          dateOfBirth: this.refs.dateOfBirth.value,
          deleteId:this.refs.deleteId.value,
          findById:this.refs.findById.value

      })

  }

  handleCreate(){

  }
  handleDeleteByName(){

  }

  handleDeleteById(){
    let url='http://localhost:8080/api/customer/'+this.state.deleteId
    axios.delete(url)
    .then(console.log("deleted"))
    .catch(function (error) {
    console.log(error);
  });
  }

  handlefindById(){
    let url='http://localhost:8080/api/customer/'+this.state.findById
    axios.get(url)
    .then(function (response){
      console.log(response)})
    .catch(function (error) {
    console.log(error);
  });
  }
  handleFindAll(){
    let url='http://localhost:8080/api/customers'
    axios.get(url)
    .then(function (response){
      console.log(response)})
    .catch(function (error) {
    console.log(error);
  });
  }
  handleFindByUsername(){

  }
  handleFindByCredentials(){

  }

  render(){
    return(

        <div className="container">
          <p className="head">RESTAURANT OWNER PANEL</p>
          <div className="row">
          <div className={"container-fluid float-left registerClass"}>
              <div>
                  <h3 className={'text-center'}>Create new Owner</h3>
                  <div className={'container text-center'} >

                      <form>
                          <div className={'form-group'}>
                              <input
                                  ref="newId"
                                  type="text"
                                  className="form-control"
                                  id="Id"
                                  placeholder={'Id'}
                                  onChange={this.update.bind(this)}
                              />
                          </div>
                          <div className="form-group">
                              <input
                                  ref="FirstName"
                                  type="text"
                                  className="form-control"
                                  id="FirstName"
                                  placeholder="First Name"
                                  onChange={this.update.bind(this)}
                              />
                          </div>
                          <div className="form-group">
                              <input
                                  ref="LastName"
                                  type="text"
                                  className="form-control"
                                  id="LastName"
                                  placeholder="Last Name"
                                  onChange={this.update.bind(this)}
                              />
                          </div>
                          <div className="form-group">
                              <input
                                  ref="Username"
                                  type="text"
                                  className="form-control"
                                  id="Username"
                                  placeholder="Username"
                                  onChange={this.update.bind(this)}
                              />
                          </div>
                          <div className="form-group">
                              <input
                                  ref="Email"
                                  type="email"
                                  className="form-control"
                                  id="Email"
                                  placeholder="Email"
                                  onChange={this.update.bind(this)}
                              />
                          </div>
                          <div className="form-group">
                              <input
                                  ref="Password"
                                  type="password"
                                  className="form-control"
                                  id="Password"
                                  placeholder="Password"
                                  onChange={this.update.bind(this)}
                              />
                          </div>
                          <div className="form-group">
                              <input
                                  ref="dateOfBirth"
                                  type="text"
                                  className="form-control"
                                  id="DateOfBith"
                                  placeholder="Date Of Birth"
                                  onChange={this.update.bind(this)}
                              />
                          </div>


                          <div>
                              <button
                                  type="submit"
                                  className="btn btn-primary m-5"
                                  onClick={this.handleClick.bind(this)}
                              >SUBMIT
                              </button>
                          </div>
                      </form>
                  </div>
              </div>
          </div>


          <div className={"container-fluid float-right registerClass"}>
              <div>
                  <h3 className={'text-center'}>Update Owner</h3>
                  <div className={'container text-center'} >

                      <form>
                          <div className={'form-group'}>
                              <input
                                  ref="newId"
                                  type="text"
                                  className="form-control"
                                  id="Id"
                                  placeholder={'Id'}
                                  onChange={this.update.bind(this)}
                              />
                          </div>
                          <div className="form-group">
                              <input
                                  ref="FirstName"
                                  type="text"
                                  className="form-control"
                                  id="FirstName"
                                  placeholder="First Name"
                                  onChange={this.update.bind(this)}
                              />
                          </div>
                          <div className="form-group">
                              <input
                                  ref="LastName"
                                  type="text"
                                  className="form-control"
                                  id="LastName"
                                  placeholder="Last Name"
                                  onChange={this.update.bind(this)}
                              />
                          </div>
                          <div className="form-group">
                              <input
                                  ref="Username"
                                  type="text"
                                  className="form-control"
                                  id="Username"
                                  placeholder="Username"
                                  onChange={this.update.bind(this)}
                              />
                          </div>
                          <div className="form-group">
                              <input
                                  ref="Email"
                                  type="email"
                                  className="form-control"
                                  id="Email"
                                  placeholder="Email"
                                  onChange={this.update.bind(this)}
                              />
                          </div>
                          <div className="form-group">
                              <input
                                  ref="Password"
                                  type="password"
                                  className="form-control"
                                  id="Password"
                                  placeholder="Password"
                                  onChange={this.update.bind(this)}
                              />
                          </div>
                          <div className="form-group">
                              <input
                                  ref="dateOfBirth"
                                  type="text"
                                  className="form-control"
                                  id="DateOfBith"
                                  placeholder="Date Of Birth"
                                  onChange={this.update.bind(this)}
                              />
                          </div>


                          <div>
                              <button
                                  type="submit"
                                  className="btn btn-primary m-5"
                                  onClick={this.handleClick.bind(this)}
                              >SUBMIT
                              </button>
                          </div>
                      </form>
                  </div>
              </div>
          </div>
</div>

<div className="row">

          <div className="col-4 container-fluid float-right registerClass2">
              <h3 className={'text-center'}>Delete Owner by id</h3>
              <div className="form-group">
                  <input
                      ref="deleteId"
                      type="text"
                      className="form-control"
                      id="Id"
                      placeholder="Id"
                      onChange={this.update.bind(this)} />
                  <button type="submit" className="btn btn-primary m-2"
                      onClick={this.handleDeleteById.bind(this)} >DELETE
                  </button>
              </div>
          </div>


          <div className="col-4 container-fluid float-right registerClass2">
              <h3 className={'text-center'}>Find Owner by id</h3>
              <div className="form-group">
                  <input
                      ref="findById"
                      type="text"
                      className="form-control"
                      id="Id"
                      placeholder="Id"
                      onChange={this.update.bind(this)}
                  />
                  <button
                      type="submit"
                      className="btn btn-primary m-2"
                      onClick={this.handlefindById.bind(this)}
                  >SUBMIT
                  </button>
              </div>
          </div>

</div>


<div className="row">

          <div className="col-4 container-fluid float-right registerClass2">
              <h3 className={'text-center'}>Delete Owner by Username</h3>
              <div className="form-group">
                  <input
                      ref="deleteByname"
                      type="text"
                      className="form-control"
                      id="Id"
                      placeholder="Id"
                      onChange={this.update.bind(this)}
                  />
                  <button
                      type="submit"
                      className="btn btn-primary m-2"
                      onClick={this.handleDeleteByName.bind(this)}
                  >DELETE
                  </button>
              </div>
          </div>


          <div className="col-4 container-fluid float-right registerClass2">
              <h3 className={'text-center'}>Find Owner by Credentials</h3>
              <div className="form-group">
                  <input
                      ref="findByCredentials"
                      type="text"
                      className="form-control"
                      id="Id"
                      placeholder="Id"
                      onChange={this.update.bind(this)}
                  />
                  <input
                      ref="findByCredentialspass"
                      type="text"
                      className="form-control"
                      id="password"
                      placeholder="password"
                      onChange={this.update.bind(this)}
                  />
                  <button
                      type="submit"
                      className="btn btn-primary m-2"
                      onClick={this.handleFindByCredentials.bind(this)}
                  >SUBMIT
                  </button>
              </div>
          </div>

</div>

<button type="submit" className="btn btn-primary m-2"
    onClick={this.handleFindAll.bind(this)} >Find All Owners</button>
    </div>


    )
  }
}
