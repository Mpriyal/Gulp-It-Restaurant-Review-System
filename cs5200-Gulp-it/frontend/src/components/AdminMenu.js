  import React from 'react'
  import axios from 'axios';

  export default class AdminMenu extends React.Component {
    constructor(props) {
      super(props);
      this.setState={
        name:null,
        description:null,
        price:null,
        type:null,
        deleteId:null,
        findById:null,
        newname:null,
        newdescription:null,
        newprice:null,
        newtype:null,
        newId:null,
        ownerId:null,
        restId:null,
        menu:[]
      }
      this.menuUpdate=this.menuUpdate.bind(this)
    }

    saveMenu(e){
      e.preventDefault();
      console.log("Success from FoodPage!");

      fetch('http://localhost:8080/api/owner/'+this.state.ownerId+'/restaurant/'+this.state.restId+'/menu', {
        method: 'POST',
        headers: {
          'Accept': 'application/json',
          'Content-Type': 'application/json',
        },
        body: JSON.stringify({
          item_name: this.state.name,
          item_type: this.state.type,
          price: this.state.price,
          description: this.state.description,
          restaurant: this.state.restId
        })
      }).then(console.log("saved menu to the db "));
    }

    menuUpdate(e){
      e.preventDefault();
      console.log(this);
      this.setState({
        name:this.refs.menuName.value,
        description:this.refs.des.value,
        price:this.refs.price.value,
        type:this.refs.types.value,
        deleteId:this.refs.deleteId.value,
        findById:this.refs.findById.value,
        newname:this.refs.newmenuName.value,
        newdescription:this.refs.newdes.value,
        newprice:this.refs.newprice.value,
        newtype:this.refs.newtypes.value,
        newId:this.refs.NewId.value,
        ownerId:this.refs.ownerId.value,
        restId:this.refs.restId.value
      })
    }
    refresh(){
      var self=this
      let menuUrl="http://localhost:8080/api/owner/"+this.state.ownerId+"/restaurant/"+this.props.restId+"/menu";
      axios.get(menuUrl).then(
        function(res){
          console.log(res)
          self.setState({
            menu:res.data
          })
        }
      )
    }

handleFindAll(){

}
    handleDeleteById(){
      let url='http://localhost:8080/api/admin/menu/'+this.state.deleteId
      axios.delete(url)
      .then(console.log("deleted"))
      .catch(function (error) {
      console.log(error);
    });
    }

    handlefindById(){
      var self = this;
      let url='http://localhost:8080/api/restaurant/'+this.state.findById+'/menu'

    axios.get(url)
      .then(function (res){
        const menu = res.data;
        self.setState({menu});
        console.log(res)})
      .catch(function (error) {
      console.log(error);
    });
    }


    render(){
      return(

        <div className="container-fluid m-2">
            <p className="head">Menu Panel</p>

          <div className={"row menu2 m-5 p-4" }>
            <div className={"col-12 text-center"}>
              <form>
              <div className={"card-header menu"}>Add Menu</div>
              <div className="form-group">
              <input
              ref="menuName"
              type="text"
              className="form-control"
              id="id"
              placeholder="Name"
              onChange={this.menuUpdate.bind(this)}
              />
              </div>
              <div className="form-group">
              <input
              ref="des"
              type="text"
              className="form-control"
              id="description"
              placeholder="Description"
              onChange={this.menuUpdate.bind(this)}
              />
              </div>
              <div className={'form-group'}>
              <input
              ref="price"
              type="text"
              className="form-control"
              id="price"
              placeholder={'Price'}
              onChange={this.menuUpdate.bind(this)}
              />
              </div>
              <div className="form-group">
              <input
              ref="types"
              type="text"
              className="form-control"
              id="type"
              placeholder="Type"
              onChange={this.menuUpdate.bind(this)}
              />
              </div>
              <div className="form-group">
              <input
              ref="restId"
              type="text"
              className="form-control"
              id="type"
              placeholder="Restaurant Id"
              onChange={this.menuUpdate.bind(this)}
              />
              </div>
              <div className="form-group">
              <input
              ref="ownerId"
              type="text"
              className="form-control"
              id="type"
              placeholder="Owner"
              onChange={this.menuUpdate.bind(this)}
              />
              </div>
                  <button
                    onClick={this.saveMenu.bind(this)}
                    type="submit"
                    className="btn btn-primary btn-sm"
                    >Save Menu
                    </button>
                  <button
                    onClick={this.refresh.bind(this)}
                    type="submit"
                    className="btn btn-primary btn-sm"
                    >Refresh
                  </button>
              </form>
            </div>
          </div>

          <div className={"row menu2 m-5 p-4" }>
            <div className={"col-12 text-center"}>
              <form>
              <div className={"card-header menu"}>Update Menu</div>
              <div className="form-group">
              <input
              ref="NewId"
              type="text"
              className="form-control"
              id="id"
              placeholder="Id"
              onChange={this.menuUpdate.bind(this)}
              />
              </div>
              <div className="form-group">
              <input
              ref="newmenuName"
              type="text"
              className="form-control"
              id="id"
              placeholder="Name"
              onChange={this.menuUpdate.bind(this)}
              />
              </div>
              <div className="form-group">
              <input
              ref="newdes"
              type="text"
              className="form-control"
              id="description"
              placeholder="Description"
              onChange={this.menuUpdate.bind(this)}
              />
              </div>
              <div className={'form-group'}>
              <input
              ref="newprice"
              type="text"
              className="form-control"
              id="price"
              placeholder={'Price'}
              onChange={this.menuUpdate.bind(this)}
              />
              </div>
              <div className="form-group">
              <input
              ref="newtypes"
              type="text"
              className="form-control"
              id="type"
              placeholder="Type"
              onChange={this.menuUpdate.bind(this)}
              />
              </div>
                  <button
                    onClick={this.saveMenu.bind(this)}
                    type="submit"
                    className="btn btn-primary btn-sm"
                    >Save Menu
                    </button>
                  <button
                    onClick={this.refresh.bind(this)}
                    type="submit"
                    className="btn btn-primary btn-sm"
                    >Refresh
                  </button>
              </form>
            </div>
          </div>

          <div className="row">

                    <div className="col-4 container-fluid float-right registerClass2">
                        <h3 className={'text-center'}>Delete Menu by id</h3>
                        <div className="form-group">
                            <input
                                ref="deleteId"
                                type="text"
                                className="form-control"
                                id="Id"
                                placeholder="Id"
                                onChange={this.menuUpdate.bind(this)} />
                            <button type="submit" className="btn btn-primary m-2"
                                onClick={this.handleDeleteById.bind(this)} >DELETE
                            </button>
                        </div>
                    </div>


                    <div className="col-4 container-fluid float-right registerClass2">
                        <h3 className={'text-center'}>Find Menu by Restaurant id</h3>
                        <div className="form-group">
                            <input
                                ref="findById"
                                type="text"
                                className="form-control"
                                id="Id"
                                placeholder="Id"
                                onChange={this.menuUpdate.bind(this)}
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
              <button type="submit" className="btn btn-primary m-2"
                  onClick={this.handleFindAll.bind(this)} >Find All customers</button>

        </div>

      )
    }
  }
