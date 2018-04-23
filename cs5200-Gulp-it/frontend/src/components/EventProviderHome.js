import React from 'react'
import RestaurantList from './RestaurantList'
import axios from "axios/index";

export default class EventProviderHome extends React.Component{
    constructor(props){
        super(props);
        this.state={
            userid:localStorage.getItem('userid'),
            id:'',
            firstname:'',
            lastname:'',
            email:'',
            username:'',
            phone:'',
            address:'',
            dob:'',
            customerKey:'',
            password:'',
            update:false,
            info:[],
            events:[
              {

              }
            ]
        };
        this.handleUpdate = this.handleUpdate.bind(this);
    }

    handleUpdate(){
        this.setState({
            update:true
        })
    }
    handleDelete(e){
      var self= this;
      var url= 'http://localhost:8080/api/eventprovider/'+localStorage.getItem('userid');
      axios.delete(url).then(
      console.log("User deleted"))
      alert("You have been deleted");
    }


    //PUT THE API TO GET THE RESTAURANTS OF THE CUSTOMERS WHICH HE HAS COMMENTED OR LIKED
    componentDidMount() {

      var self = this
      var url="http://localhost:8080/api/eventprovider/"+this.state.userid
      axios.get(url).then(
        function(res){
          console.log("data of the user is")
          console.log(res);
          self.setState({
          id:res.data.id,
          firstname:res.data.firstName,
          lastname:res.data.lastName,
          email:res.data.email,
          username:res.data.username,
          phone:res.data.phoneList,
          address:res.data.addressList,
          dob:res.data.dob,
          customerKey:res.data.event_key
        }
          )
        }
      )
      var url1="http://localhost:8080/api/eventprovider/"+this.state.userid+"/event"
      axios.get(url1).then(

        function(res){
          console.log(res)
          const events = res.data;
            self.setState({events});
          })
        }

        handleDeleteevent(id,index){
        var deleteurl="http://localhost:8080/api/eventprovider/"+localStorage.getItem('userid')+"/event/"+id
          console.log(id)
          axios.delete(deleteurl).then(
            console.log("Event deleted")
          )
          var array = this.state.events;
          array.splice(index, 1);
          this.setState({
            events:array
          })
        }
handleAddRestToEvent(e){
    e.preventDefault();
    var url="http://localhost:8080/api/eventprovider/"+this.state.userid+"/event/"+this.state.eId+"/restaurant/"+this.state.rId;
    axios.post(url).then(
      console.log("Added restaunrant to event")
    )
}

    handleMoreinfoevent(id){
      console.log(id);
      var self = this
      var url="http://localhost:8080/api/eventprovider/"+this.state.userid+"/event/"+id+"/restaurant"
      console.log(url);
      axios.get(url).then(
        function(res){
          console.log(res);
          self.setState({
            info:res.data
          })
          console.log(self)
        }
      )
    }

    update(e){

          console.log(this);
          this.setState({
              ename: this.refs.ename.value,
              edescription: this.refs.edescription.value,
              edate: this.refs.edate.value,
              rId:this.refs.rId.value,
              eId:this.refs.eId.value
            })
      }

handleClick(e){
  e.preventDefault();
  var url = "http://localhost:8080/api/eventprovider/"+this.state.userid+"/event"
  axios.post(url,{
    event_name:this.state.ename,
    	description:this.state.edescription,
    	date:this.state.edate
  }
).then(
  console.log("event added")
)
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
            <div className="profile3 col-3">
                <h1>{this.state.firstname} {this.state.lastname}</h1>
                <p>Email: {this.state.email}</p>
                <p>Username: {this.state.username}</p>
                <p>Id: {this.state.id}</p>
                <p>Phone: {this.state.phone}</p>
                <p>Address: {this.state.address}</p>
                <p>Date of Birth: {this.state.dob}</p>
                <p>Customer Key: {this.state.customerKey}</p>
                <p><button className={"btn btn-primary"}
                onClick={this.handleUpdate}>Update</button></p>
                <p><button className={"btn btn-danger"}
                onClick={this.handleDelete}>Delete</button></p>
            </div>

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
              <div className="container col-4">
                <p className="head text-center"> Add New Event</p>
                                        <form className={'m-5'}>
                                            <div className={'form-group'}>
                                                <input
                                                    ref="ename"
                                                    type="text"
                                                    className="form-control"
                                                    id="name"
                                                    placeholder={'Name'}
                                                    onChange={this.update.bind(this)}
                                                />
                                            </div>
                                            <div className="form-group">
                                                <input
                                                    ref="edescription"
                                                    type="text"
                                                    className="form-control"
                                                    id="description"
                                                    placeholder="Description"
                                                    onChange={this.update.bind(this)}
                                                />
                                            </div>
                                            <div className="form-group">
                                                <input
                                                    ref="edate"
                                                    type="text"
                                                    className="form-control"
                                                    id="Date"
                                                    placeholder="Date"
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

                                          <p className="head text-center"> Add Restaurant to Event</p>
                                        <form className={'m-5'}>
                                            <div className={'form-group'}>
                                                <input
                                                    ref="rId"
                                                    type="text"
                                                    className="form-control"
                                                    id="name"
                                                    placeholder={'Restaurant Id'}
                                                    onChange={this.update.bind(this)}
                                                />
                                            </div>
                                            <div className="form-group">
                                                <input
                                                    ref="eId"
                                                    type="text"
                                                    className="form-control"
                                                    id="description"
                                                    placeholder="Event Id"
                                                    onChange={this.update.bind(this)}
                                                />
                                            </div>
                                            <div>
                                                <button
                                                    type="submit"
                                                    className="btn btn-primary m-5"
                                                    onClick={this.handleAddRestToEvent.bind(this)}
                                                >SUBMIT
                                                </button>
                                            </div>
                                        </form>
                                        </div>
                                        <RestaurantList data2={this.state.info}/>
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
