import React from 'react'

export default class AdminMenu extends React.Component {
  constructor(props) {
    super(props)

  }

  saveFood(e){
      e.preventDefault();
      console.log("Success from FoodPage!");

      fetch('http://localhost:8080/api/owner/31/restaurant/2/food', {
          method: 'POST',
          headers: {
              'Accept': 'application/json',
              'Content-Type': 'application/json',
          },
          body: JSON.stringify({
              name:this.state.fname,
              price:this.state.fprice,
              description:this.state.fdescription,
              Vegetarian:this.state.veg

          })
      }).then(console.log("saved to the db"));

      this.forceUpdate();
  }
  saveDrink(e){
      e.preventDefault();
      console.log("Success from FoodPage!");

      fetch('http://localhost:8080/api/owner/31/restaurant/2/drinks', {
          method: 'POST',
          headers: {
              'Accept': 'application/json',
              'Content-Type': 'application/json',
          },
          body: JSON.stringify({
              name:this.state.dname,
              price:this.state.dprice,
              description:this.state.ddescription,
              liquor:this.state.alcoholic

          })
      }).then(console.log("saved to the db"));
      this.renderNormal();
  }
foodUpdate(){
      this.setState({
          fname:this.refs.fname.value,
          fdescription:this.refs.fdes.value,
          fprice:this.refs.fprice.value,
          veg:this.refs.veg.value
      })
  }

  drinkUpdate(){
      this.setState({
          dname:this.refs.dname.value,
          ddescription:this.refs.ddes.value,
          dprice:this.refs.dprice.value,
          alcoholic:this.refs.alc.value
      })
  }

  render(){
    return(
      <div className="container-fluid m-2">
      <p className="head">Menu Panel</p>
      <div className={"row menu2 m-5 p-4" }>
          <div className={"col-6"}>
              <form>
                 <div className={"card-header menu"}>Add Food</div>
                  <div className="form-group">
                      <input
                          ref="fname"
                          type="text"
                          className="form-control"
                          id="id"
                          placeholder="Name"
                          onChange={this.foodUpdate.bind(this)}
                      />
                  </div>
                  <div className="form-group">
                      <input
                          ref="fdes"
                          type="text"
                          className="form-control"
                          id="LastName"
                          placeholder="Description"
                          onChange={this.foodUpdate.bind(this)}
                      />
                  </div>
                  <div className={'form-group'}>
                      <input
                          ref="fprice"
                          type="text"
                          className="form-control"
                          id="price"
                          placeholder={'Price'}
                          onChange={this.foodUpdate.bind(this)}
                      />
                  </div>
                  <div className="form-group">
                      <input
                          ref="veg"
                          type="text"
                          className="form-control"
                          id="veg"
                          placeholder="Vegetarian"
                          onChange={this.foodUpdate.bind(this)}
                      />
                  </div>
                  <button
                      onClick={this.saveFood.bind(this)}
                      type="submit"
                      className="btn btn-primary"
                  >Save Food
                  </button>
              </form>
          </div>
          <div className={"col-6"}>
              <form>
                  <div className={"card-header menu"}>Add Drinks</div>
                  <div className="form-group">
                      <input
                          ref="dname"
                          type="text"
                          className="form-control"
                          id="id"
                          placeholder="Name"
                          onChange={this.drinkUpdate.bind(this)}
                      />
                  </div>
                  <div className="form-group">
                      <input
                          ref="ddes"
                          type="text"
                          className="form-control"
                          id="LastName"
                          placeholder="Description"
                          onChange={this.drinkUpdate.bind(this)}
                      />
                  </div>

                  <div className={'form-group'}>
                      <input
                          ref="dprice"
                          type="text"
                          className="form-control"
                          id="price"
                          placeholder={'Price'}
                          onChange={this.drinkUpdate.bind(this)}
                      />
                  </div>

                  <div className="form-group">
                      <input
                          ref="alc"
                          type="text"
                          className="form-control"
                          id="veg"
                          placeholder="Alcoholic"
                          onChange={this.drinkUpdate.bind(this)}
                      />
                  </div>
                  <button
                      onClick={this.saveDrink.bind(this)}
                      type="submit"
                      className="btn btn-primary"
                  >Save Drinks
                  </button>
              </form>
          </div>
          </div>
      </div>

    )
  }
}
