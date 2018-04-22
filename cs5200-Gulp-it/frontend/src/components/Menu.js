import React from 'react'
import axios from 'axios';


export default class Menu extends React.Component {
  constructor(props) {
    super(props);
    this.props={
      menu:[]
    }

  }
  componentDidMount(){
    this.se
    this.state.menu
  }
  handleDelete(id,index){
  var testUrl="http://localhost:8080/api/owner/31/restaurant/1/menu/"+id
    console.log(id)
    axios.delete(testUrl).then(
      console.log("menu deleted")
    )
    var array = this.state.menu;
    array.splice(index, 1);
    this.setState({
      menu:array
    })
  }
  handleUpdate(){

  }
  render(){
    return(
      <div>
      <p className="head">
      The Menu:
      </p>
      <table className="table table-dark m-t-5">
          <thead>
            <tr>
              <th scope="col">Id</th>
              <th scope="col">Name</th>
              <th scope="col">Type</th>
              <th scope="col">Price</th>
              <th scope="col">Description</th>
            </tr>
          </thead>
          <tbody>

      {
        this.state.menu.map((menuitem,index)=>
        <tr key={index}>
        <th scope="row">{menuitem.id}</th>
        <td>{menuitem.item_name}</td>
        <td>{menuitem.item_type}</td>
        <td>{menuitem.price}</td>
        <td>{menuitem.description}</td>
        <td><button className="btn btn-danger btn-sm" onClick={this.handleDelete.bind(this,menuitem.id,index)}> Delete</button></td>
        <td><button className="btn btn-success btm-sm"onClick={this.handleUpdate.bind(this,menuitem.id,index)}> Update</button></td>
        </tr>
      )
      }
          </tbody>
        </table>
        </div>
    )
  }
}
