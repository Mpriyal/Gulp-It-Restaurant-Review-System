import React from 'react'
import axios from 'axios';
import OwnerCard from './OwnerCard'
import AdminSingle from './AdminSingle'


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
      findById:null,
      FindByUsername:null,
      owner:[],
      showcard:false,
      singlecard:false
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
              customerKey: "",
              type:'Restaurant Owner'
          })
      }).then(
        console.log("saved to the db"));
  }

  update(){
      console.log(this);
      this.setState({
          id:this.refs.Id.value,
          idupdated:this.refs.newId.value,
          firstName: this.refs.FirstName.value,
          lastName: this.refs.LastName.value,
          username: this.refs.Username.value,
          email:this.refs.Email.value,
          password:this.refs.Password.value,
          dateOfBirth: this.refs.dateOfBirth.value,
          newfirstName: this.refs.newFirstName.value,
          newlastName: this.refs.newLastName.value,
          newusername: this.refs.newUsername.value,
          newemail:this.refs.newEmail.value,
          newpassword:this.refs.newPassword.value,
          newdateOfBirth: this.refs.newdateOfBirth.value,
          deleteId:this.refs.deleteId.value,
          findById:this.refs.findById.value,
          FindByUsername:this.refs.FindByUsername.value,
          findByCredentialspass:this.refs.findByCredentialspass.value,
          findByCredentials:this.refs.findByCredentials.value
      })

  }

  handleCreate(e){
    e.preventDefault();
    console.log("Success from createPage!")
    fetch('http://localhost:8080/api/owner', {
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
            ownerKey: ""

        })
    }).then(console.log("saved to the db"));

  }


  //api not available
  handleDeleteByName(){

  }

  handleDeleteById(){
    let url='http://localhost:8080/api/owner/'+this.state.deleteId
    axios.delete(url)
    .then(console.log("deleted"))
    .catch(function (error) {
    console.log(error);
  });
  }

  handlefindById(){
    var self = this;
    let url='http://localhost:8080/api/owner/'+this.state.findById
    axios.get(url)
    .then(function (res){
      const owner = res.data;
      self.setState({owner});
      self.setState({
        singlecard:true
      }
      )
      console.log(res)})
    .catch(function (error) {
    console.log(error);
  });
  }
  handleFindAll(){
    var self = this;
    let url='http://localhost:8080/api/owner'
    axios.get(url)
    .then(function (res){
        const owner = res.data;
        self.setState({owner});
        self.setState({
          showcard:true
        }
        )
        // owner:response.data,
        // showcard:true
      console.log(self);
      console.log(res)})
    .catch(function (error) {
    console.log(error);
  });
  }
  handleFindOwnerByName(){
    var self = this;
    let url='http://localhost:8080/api/owner'
    axios.get(url,{
        params:{
          username:this.state.FindByUsername
        }
    })
    .then(function (res){
      const owner = res.data;
      self.setState({owner});
      self.setState({
        showcard:true
      }
      )
      console.log(res)})
    .catch(function (error) {
    console.log(error);
    });
  }
  handleFindByCredentials(){
    var self = this;
    let url='http://localhost:8080/api/owner'
    axios.get(url,{
        params:{
          username:this.state.findByCredentials,
          password:this.state.findByCredentialspass
        }
    })
    .then(function (res){
      const owner = res.data;
      self.setState({owner});
      self.setState({
        showcard:true
      }
      )
      console.log(res)})
    .catch(function (error) {
    console.log(error);
    });
  }

handleUpdatebutton(e){
  e.preventDefault();
  console.log("Success from updatePage!")
  let url='http://localhost:8080/api/owner/'+this.state.idupdated
  fetch(url ,{
      method: 'PUT',
      headers: {
          'Accept': 'application/json',
          'Content-Type': 'application/json',
      },
      body: JSON.stringify({
          firstName: this.state.newfirstName,
          lastName: this.state.newlastName,
          username:  this.state.newusername,
          password:  this.state.newpassword,
          email:  this.state.newemail,
          dob: this.state.newdateOfBirth,
          ownerKey: ""
      })
  }).then(console.log("update in the db"));

}
render(){
  if(!this.state.showcard){
    if(!this.state.singlecard){
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
                                  ref="Id"
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
                          <div className="form-group">
                              <input
                                  ref="typeofperson"
                                  type="text"
                                  className="form-control"
                                  id="type"
                                  placeholder="Type"
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
                                  ref="newFirstName"
                                  type="text"
                                  className="form-control"
                                  id="FirstName"
                                  placeholder="First Name"
                                  onChange={this.update.bind(this)}
                              />
                          </div>
                          <div className="form-group">
                              <input
                                  ref="newLastName"
                                  type="text"
                                  className="form-control"
                                  id="LastName"
                                  placeholder="Last Name"
                                  onChange={this.update.bind(this)}
                              />
                          </div>
                          <div className="form-group">
                              <input
                                  ref="newUsername"
                                  type="text"
                                  className="form-control"
                                  id="Username"
                                  placeholder="Username"
                                  onChange={this.update.bind(this)}
                              />
                          </div>
                          <div className="form-group">
                              <input
                                  ref="newEmail"
                                  type="email"
                                  className="form-control"
                                  id="Email"
                                  placeholder="Email"
                                  onChange={this.update.bind(this)}
                              />
                          </div>
                          <div className="form-group">
                              <input
                                  ref="newPassword"
                                  type="password"
                                  className="form-control"
                                  id="Password"
                                  placeholder="Password"
                                  onChange={this.update.bind(this)}
                              />
                          </div>
                          <div className="form-group">
                              <input
                                  ref="newdateOfBirth"
                                  type="text"
                                  className="form-control"
                                  id="DateOfBith"
                                  placeholder="Date Of Birth"
                                  onChange={this.update.bind(this)}
                              />
                          </div>
                          <div className="form-group">
                              <input
                                  ref="newtypeofperson"
                                  type="text"
                                  className="form-control"
                                  id="type"
                                  placeholder="Type"
                                  onChange={this.update.bind(this)}
                              />
                          </div>


                          <div>
                              <button
                                  type="submit"
                                  className="btn btn-primary m-5"
                                  onClick={this.handleUpdatebutton.bind(this)}
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
              <h3 className={'text-center'}>Find Owner by Username</h3>
              <div className="form-group">
                  <input
                      ref="FindByUsername"
                      type="text"
                      className="form-control"
                      id="Id"
                      placeholder="Id"
                      onChange={this.update.bind(this)}
                  />
                  <button
                      type="submit"
                      className="btn btn-primary m-2"
                      onClick={this.handleFindOwnerByName.bind(this)}
                  >SUBMIT
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
    onClick={this.handleFindAll.bind(this)} >Find All customers</button>
    </div>
  )}
  else if(this.state.singlecard){
    return(
      <AdminSingle data={this.state.customer}/>
    )
  }
}
  else {
    return (
      <customerCard data={this.state.customer}/>
    )
  }
  
}
}
