import React from 'react';
import axios from 'axios';
import Navbar from "./Navbar";

export default class Moreinfo extends React.Component{
    constructor(props){
        super(props);
        this.state={

        }
    }

    componentDidMount(){
       const string = 'http://localhost:8080/api/restaurant/12';
        axios.get(string)
            .then(res=>{
                console.log(res)
                this.setState({
                    id:res.data.id,
                    name:res.data.name,
                    description:res.data.description,
                    image_link:res.data.image_link,
                    cost_for_two:res.data.cost_for_two,
                    restaurant_owner:res.data.restaurant_owner,
                    restaurant_type:res.data.restaurant_type
                })
            }).then(console.log(this))
        const string2=
        axios.get(string2)
    }

    restaurantType(){
        if(this.state.restaurant_type===1){
            return "Fast Food"
        }
        else if (this.state.restaurant_type===2){
            return "Fine Dine"
        }
        else if (this.state.restaurant_type===3){
            return "Cafe"
        }
        else return "Bar"
    }

    render(){
        console.log(this);
        return(
            <div className={"row"}>

            <div className="profile col-3">
                <h1>{this.state.name}</h1>
                <img src={this.state.image_link} alt={"restaurant pic not available"}/>
                <p>Id: {this.state.id}</p>
                <p>Description: {this.state.description}</p>
                <p>Cost For Two: {this.state.cost_for_two}</p>
                <p>Restaurant Owner: {this.state.restaurant_owner}</p>
                <p>Restaurant Type: {this.restaurantType()}</p>
            </div>
                <div className="col-8">
                    <h1>
                        Comments
                    </h1>
                </div>

            </div>)
   }
}