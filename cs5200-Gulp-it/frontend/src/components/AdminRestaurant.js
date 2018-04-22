import React from 'react'
import axios from 'axios';
import OwnerCard from './OwnerCard'
import AdminSingle from './AdminSingle'
import RestaurantList from './RestaurantList'


export default class AdminRestaurant extends React.Component {
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
      singlecard:false,
      ownerKey:""
    }
  }

  // posting new Owners

  handleClick(e) {
      e.preventDefault();
      console.log("Success from RegisterPage!")
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
              owner_key: this.state.ownerKey,
              type:'Restaurant Owner'
          })
      }).then(
        console.log("saved to the db"));
  }

  update(){
      console.log(this);
      this.setState({
        name: this.refs.name.value,
        description: this.refs.des.value,
        image_link: this.refs.imglink.value,
        cost_for_two: this.refs.cft.value,
        type: this.refs.newtype.value,
        newname: this.refs.newname.value,
        newdescription: this.refs.newdes.value,
        newimage_link: this.refs.newimglink.value,
        newcost_for_two: this.refs.newcft.value,
        newtype: this.refs.newtype.value,
          deleteId:this.refs.deleteId.value,
          findById:this.refs.findById.value,
          FindByUsername:this.refs.FindByUsername.value,
          Owner:this.refs.Owner.value
      })

  }

  handleCreate(e){
    e.preventDefault();
    console.log("Success from createPage!")
    axios.post('http://localhost:8080/api/restaurant',{
        id: this.state.Owner,
       name:this.state.name,
       description: this.state.description,
       image_link: this.state.image_link,
       cost_for_two: this.state.cost_for_two,
       restaurant_type: this.state.type
        })
  }


  //api not available
  handleDeleteByName(){

  }

  handleDeleteById(){
    let url='http://localhost:8080/api/restaurant/'+this.state.deleteId
    axios.delete(url)
    .then(console.log("deleted"))
    .catch(function (error) {
    console.log(error);
  });
  }

  handlefindById(){
    var self = this;
    let url='http://localhost:8080/api/restaurant/'+this.state.findById
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
    console.log(this);
    var self = this;
    let url='http://localhost:8080/api/restaurant'
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
  handleFindRestaurantByName(){
    var self = this;
    let url='http://localhost:8080/api/restaurant'
    axios.get(url,{
        params:{
          name:this.state.FindByUsername
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


  save(e){
    e.preventDefault();
    var url='http://localhost:8080/api/owner/'+this.state.Owner +'/restaurant'
    console.log("Success from posting rest page!")
    fetch(url, {
        method: 'POST',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json',
        },
        body: JSON.stringify({
              name: this.state.name,
              description: this.state.description,
              image_link: this.state.image_link,
              cost_for_two: this.state.cost_for_two,
              restaurant_type: this.state.type
        })
    }).then(console.log("saved to the db"));
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
          // ownerKey: ""
      })
  }).then(console.log("update in the db"));

}
render(){
  console.log(this);
  if(!this.state.showcard){
    if(!this.state.singlecard){
  return(


        <div className="container">
          <p className="head">RESTAURANT PANEL</p>
          <div className="row">
          <div className={"container-fluid float-left registerClass"}>
              <div>
                  <h3 className={'text-center'}>Create new Restaurant</h3>
                  <div className={'container text-center'} >

                  <form>
                  <div className="form-group">
                      <input
                          ref="Owner"
                          type="text"
                          className="form-control"
                          id="id"
                          placeholder="Owner"
                          onChange={this.update.bind(this)}
                      />
                  </div>
                      <div className="form-group">
                          <input
                              ref="name"
                              type="text"
                              className="form-control"
                              id="id"
                              placeholder="Name"
                              onChange={this.update.bind(this)}
                          />
                      </div>
                      <div className="form-group">
                          <input
                              ref="des"
                              type="text"
                              className="form-control"
                              id="LastName"
                              placeholder="Description"
                              onChange={this.update.bind(this)}
                          />
                      </div>

                      <div className={'form-group'}>
                          <input
                              ref="imglink"
                              type="text"
                              className="form-control"
                              id="Id"
                              placeholder={'Image Link'}
                              onChange={this.update.bind(this)}
                          />
                      </div>

                      <div className="form-group">
                          <input
                              ref="cft"
                              type="text"
                              className="form-control"
                              id="cost for two"
                              placeholder="cost for two"
                              onChange={this.update.bind(this)}
                          />
                      </div>


                      <div className="form-group">
                          <input
                              ref="type"
                              type="text"
                              className="form-control"
                              id="type"
                              placeholder="type"
                              onChange={this.update.bind(this)}
                          />
                      </div>

                      <button
                          onClick={this.save.bind(this)}
                          type="submit"
                          className="btn btn-primary"
                      >Save
                      </button>
                  </form>
                  </div>
              </div>
          </div>


          <div className={"container-fluid float-right registerClass"}>
              <div>
                  <h3 className={'text-center'}>Update Restaurant</h3>
                  <div className={'container text-center'} >

                  <form>
                      <div className="form-group">
                          <input
                              ref="newname"
                              type="text"
                              className="form-control"
                              id="id"
                              placeholder="Name"
                              onChange={this.update.bind(this)}
                          />
                      </div>
                      <div className="form-group">
                          <input
                              ref="newdes"
                              type="text"
                              className="form-control"
                              id="LastName"
                              placeholder="Description"
                              onChange={this.update.bind(this)}
                          />
                      </div>

                      <div className={'form-group'}>
                          <input
                              ref="newimglink"
                              type="text"
                              className="form-control"
                              id="Id"
                              placeholder={'Image Link'}
                              onChange={this.update.bind(this)}
                          />
                      </div>

                      <div className="form-group">
                          <input
                              ref="newcft"
                              type="text"
                              className="form-control"
                              id="cost for two"
                              placeholder="cost for two"
                              onChange={this.update.bind(this)}
                          />
                      </div>


                      <div className="form-group">
                          <input
                              ref="newtype"
                              type="text"
                              className="form-control"
                              id="type"
                              placeholder="type"
                              onChange={this.update.bind(this)}
                          />
                      </div>

                      <button
                          onClick={this.save.bind(this)}
                          type="submit"
                          className="btn btn-primary"
                      >Save
                      </button>
                  </form>
                  </div>
              </div>
          </div>
</div>

<div className="row">

          <div className="col-4 container-fluid float-right registerClass2">
              <h3 className={'text-center'}>Delete Restaurant by id</h3>
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
              <h3 className={'text-center'}>Find Restaurant by id</h3>
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
              <h3 className={'text-center'}>Find Restaurant by Name</h3>
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
                      onClick={this.handleFindRestaurantByName.bind(this)}
                  >SUBMIT
                  </button>
              </div>
          </div>



</div>


<button type="submit" className="btn btn-primary m-2"
    onClick={this.handleFindAll.bind(this)} >Find All Restaurants</button>
    </div>
  )}
  else if(this.state.singlecard){
    return(
      <div>

        <h1>{this.state.owner.name}</h1>
        <p>{this.state.owner.description}</p>
        <p>{this.state.owner.image_link}</p>
        <p>{this.state.owner.cost_for_two}</p>
        <p>{this.state.owner.restaurant_type}</p>
        <p>{}</p>
      </div>
    )
  }


}
else {
  return (
    <RestaurantList data2={this.state.owner}/>
  )
}
}
}
