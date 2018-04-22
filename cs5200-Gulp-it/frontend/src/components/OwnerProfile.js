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
            addnew:false
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
    update() {
        this.setState(
            {
                firstname: this.refs.newfirstName.value,
                lastname: this.refs.newlastName.value,
                email: this.refs.newEmail.value,
                password: this.refs.newPassword.value,
                id: this.refs.newId.value,
                username: this.refs.newusername.value,
                phone: this.refs.newphone.value,
                address:this.refs.newaddress.value,
                dob:this.refs.newdob.value,
                customerKey:this.refs.newck.value,
                update: false,

            });
            axios({
                    method: 'post',
                    url: '/user/12345',
                    data: {
                      firstName: 'Fred',
                      lastName: 'Flintstone'
                    }
                  });


        //PUT THE PUR REQUEST TO UPDATE THE CUSTOMER
        fetch('http://localhost:8080/api/customer/', {
            method: 'PUT',
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
    renderUpdate(){
        return (
            <div className={'profile col-3'}>
                <p className={"head"}>Update Profile</p>
                <form>
                    <div className="form-group">
                        <input
                            ref="newfirstName"
                            type="text"
                            className="form-control"
                            id="FirstName"
                            placeholder="Name"
                            defaultValue={this.state.firstname}
                        />
                    </div>
                    <div className="form-group">
                        <input
                            ref="newlastName"
                            type="text"
                            className="form-control"
                            id="LastName"
                            placeholder="Name"
                            defaultValue={this.state.lastname}
                        />
                    </div>

                    <div className={'form-group'}>
                        <input
                            ref="newId"
                            type="text"
                            className="form-control"
                            id="Id"
                            placeholder={'Id'}
                            defaultValue={this.state.id}
                        />
                    </div>

                    <div className="form-group">
                        <input
                            ref="newEmail"
                            type="email"
                            className="form-control"
                            id="Email"
                            placeholder="Email"
                            defaultValue={this.state.email}
                        />
                    </div>


                    <div className="form-group">
                        <input
                            ref="newusername"
                            type="text"
                            className="form-control"
                            id="username"
                            placeholder="Username"
                            defaultValue={this.state.username}
                        />
                    </div>
                    <div className="form-group">
                        <input
                            ref="newPassword"
                            type="password"
                            className="form-control"
                            id="Password"
                            placeholder="Password"
                            defaultValue={this.state.password}
                        />
                    </div>
                    <div className="form-group">
                        <input
                            ref="newphone"
                            type="text"
                            className="form-control"
                            id="phone"
                            placeholder="phone"
                            defaultValue={this.state.phone}
                        />
                    </div>
                    <div className="form-group">
                        <input
                            ref="newaddress"
                            type="text"
                            className="form-control"
                            id="address"
                            placeholder="address"
                            defaultValue={this.state.address}
                        />
                    </div>
                    <div className="form-group">
                        <input
                            ref="newdob"
                            type="text"
                            className="form-control"
                            id="dob"
                            placeholder="Date of Birth"
                            defaultValue={this.state.dob}
                        />
                    </div>
                    <div className="form-group">
                        <input
                            ref="newck"
                            type="text"
                            className="form-control"
                            id="customerKey"
                            placeholder="Customer Key"
                            defaultValue={this.state.customerKey}
                        />
                    </div>

                    <button
                        onClick={this.update.bind(this)}
                        type="submit"
                        className="btn btn-primary"
                    >Save
                    </button>
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
