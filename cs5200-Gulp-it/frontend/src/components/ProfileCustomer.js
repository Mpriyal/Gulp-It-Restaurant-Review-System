import React from 'react'
import RestaurantList from './RestaurantList'
import axios from "axios/index";

export default class ProfileCustomer extends React.Component{
    constructor(props){
        super(props);
        this.state={
            id:'123',
            firstname:'Aman',
            lastname:'Rayat',
            email:'amanrayat32@gmail.com',
            username:'amanrayat',
            phone:'8574246016',
            address:'73 saint alp',
            dob:'null',
            customerKey:'aman123',
            password:'',
            update:false,
            favRest:[],
            commentedRestaurants:[]
        };
        this.handleUpdate = this.handleUpdate.bind(this);
    }

    handleUpdate(){
        this.setState({
            update:true
        })
    }
    //PUT THE API TO GET THE RESTAURANTS OF THE CUSTOMERS WHICH HE HAS COMMENTED OR LIKED
    componentDidMount() {
        axios.get('http://opentable.herokuapp.com/api/restaurants', {
            params: {
                name: 'boston'
            }
        })
            .then(res => {
                console.log(res);
                const favRest = res.data.restaurants;
                this.setState({favRest});
            });

        axios.get('http://opentable.herokuapp.com/api/restaurants', {
            params: {
                name: 'boston'
            }
        })
            .then(res => {
                console.log(res);
                const commentedRestaurants = res.data.restaurants;
                this.setState({commentedRestaurants});
            });

    }
    //     const string2 = 'http://localhost:8080/api/feedback/1';
    //     axios.get(string2)
    //         .then(result => {
    //             console.log(result);
    //             this.setState({
    //                 commentedRestaurants: result.data
    //             })
    //         }).then(console.log(this));
    // }

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
                       <h1>Update Profile</h1>
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
                <h1>{this.state.firstname} {this.state.lastname}</h1>
                <p>Email: {this.state.email}</p>
                <p>Username: {this.state.username}</p>
                <p>Id: {this.state.id}</p>
                <p>Phone: {this.state.phone}</p>
                <p>Address: {this.state.address}</p>
                <p>Date of Birth: {this.state.dob}</p>
                <p>Customer Key: {this.state.customerKey}</p>
                <p><button className={"btn btn-primary"} onClick={this.handleUpdate}>Update</button></p>
            </div>
                <div className="col-9">
                    <p className={"head"}>Your Favourite Restaurant</p>
                    <RestaurantList data={this.state.commentedRestaurants}/>
                    <p className={"head"}>Restaurants where you have commented</p>
                    <RestaurantList data={this.state.favRest}/>
                </div>
            </div>
                )

    }


    render(){
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