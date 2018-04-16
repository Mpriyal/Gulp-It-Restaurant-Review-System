import React from 'react'
import MoreinfoCustomer from './MoreinfoCustomer'

export default class RestaurantList extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            id:null,
            //role is for owner functionality
            role:''
        }
    }

    //add more info funcitionality
    handleButton(ret){
        console.log(ret);
        this.setState(
            {
                id:ret
            }
        )
    }
    render()
    {

        return (

            <div className={"container p-5 m-5"}>
                <div className="row">
                    {this.props.data.map((restaurant)=>

                        <div className="col-sm-4" key={restaurant.id}>
                            <div className="card">
                                <img className="card-img-top" src={restaurant.image_url} alt="Card image cap"/>
                                <div className="card-body">
                                    <h5 className="card-title">{restaurant.name}</h5>
                                    <p className="card-text">{restaurant.address} {restaurant.city} {restaurant.state} </p>
                                    <p className="card-text">Call : {restaurant.phone}</p>
                                    <p className="card-text"><a href={restaurant.reserve_url}>Reserve   </a>
                                        <button className={"btn btn-primary btn-sm"}
                                                onClick={() => this.handleButton(restaurant.id)}>More info</button></p>
                                </div>
                            </div>
                        </div>
                    )}
                </div>
            </div>
        );
    }
}