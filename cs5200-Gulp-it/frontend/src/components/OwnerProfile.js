import React from 'react';
import axios from "axios/index";
import RestaurantList from './RestaurantList'
import AddnewRestaurant from './AddnewRestaurant'

export default class OwnerProfile extends React.Component{
    constructor(props){
        super(props);
        this.state={
            userid:localStorage.getItem('userid'),
            update:false,
            favRest:[],
            addnew:false,

        }
        this.handleUpdate = this.handleUpdate.bind(this);
    }
    handleUpdate(){
        this.setState({
            update:true
        })
    }
    componentDidMount() {
      var self = this;
        // give the route to all restaurants of the pwner
        var url='http://localhost:8080/api/owner/'+this.state.userid+'/restaurant'
        axios.get(url,
        )
            .then(res => {
                console.log(res);
                const favRest = res.data;
                this.setState({favRest});
            });

          var url2='http://localhost:8080/api/owner/'+this.state.userid
    axios.get(url2).then(

      function(res){
        console.log(res);
        self.setState({
          firstName:res.data.firstName,
          lastname: res.data.lastName,
          email: res.data.email,
          password: res.data.password,
          id: res.data.id,
          username: res.data.username,
          dob:res.data.dateOfBirth,
          customerKey:res.data.customerKey
        })
      }
    )
}

    addNew(){
        this.setState({
            addnew:true
        })
    }

    handleDelete(e){
      var self= this;
      var url= 'http://localhost:8080/api/owner/'+localStorage.getItem('userid');
      axios.delete(url).then(
      console.log("owner deleted"))
      // self.props.history.push('/')
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
            ownerKey:this.refs.key.value,

        })

    }

    handleUpdatebutton(e){
      e.preventDefault();
      console.log("Success from updatePage!")
      let url='http://localhost:8080/api/owner/'+this.state.userid
      axios.put(url ,({
              id:this.state.userid,
              firstName: this.state.firstName,
              lastName: this.state.lastName,
              username:  this.state.username,
              password:  this.state.password,
              email:  this.state.email,
              dob: this.state.dateOfBirth,
              ownerKey:this.state.ownerKey

          }))
    .then(console.log("update in the db"))
      this.setState({
        update:false
      })

    }
    renderUpdate(){
        return (
            <div className={'profile col-3'}>
                <p className={"head"}>Update Profile</p>

                                      <form>

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
                                                  ref="key"
                                                  type="text"
                                                  className="form-control"
                                                  id="type"
                                                  placeholder="OwnerKey"
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
        )
    }

    renderProfile(){
        return(
            <div className={"row"}>
                <div className="profile col-3">
                    <h1>{this.state.firstName} {this.state.lastname}</h1>
                    <p>Email: {this.state.email}</p>
                    <p>Username: {this.state.username}</p>
                    <p>Id: {this.state.id}</p>
                    <p>Phone: {this.state.phone}</p>
                    <p>Address: {this.state.address}</p>
                    <p>Date of Birth: {this.state.dob}</p>
                    <p>Owner Key: {this.state.customerKey}</p>
                    <p><button className={"btn btn-primary"} onClick={this.handleUpdate}>Update</button></p>
                    <p><button className={"btn btn-danger"} onClick={this.handleDelete}>Delete</button></p>
                </div>

                <div className="col-9">

                    <p className={"head"}>Restaurant Information</p>
                        <div className={" container"}>
                    <button className={"btn btn-primary"} onClick={this.addNew.bind(this)}>Add New Restaurant</button>

                    </div>
                    <RestaurantList data2={this.state.favRest}/>
                    </div>
            </div>
        )
    }
    render(){
        if(this.state.addnew===true){
            return (
                <AddnewRestaurant/>
            )
        }
        if(this.state.update===false){
            return (
                this.renderProfile()
            )
        }
        else {
            return this.renderUpdate();
        }

    }
}
