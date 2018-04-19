import React from 'react'
import MoreinfoCustomer from './MoreinfoCustomer'

export default class RestaurantList extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            id:null,
            //role is for owner functionality
            role:'',
            info:false,
            restId:null
        }
    }

    //add more info funcitionality
    handleButton(restid){
        console.log(restid);
        this.setState(
            {
                restId:restid,
                info:true
            }
        )
    }
    render()
    {console.log(this);
        if(!this.state.info) {
            return (
                <div className={"container p-5 m-5"}>
                    <div className="row">
                        {this.props.data.map((restaurant,index) =>

                            <div className="col-sm-4" key={restaurant.id}>
                                <div className="card">
                                    <img className="card-img-top" src={restaurant.image_url} alt="Card image cap"/>
                                    <div className="card-body">
                                        <h5 className="card-title">{restaurant.name}</h5>
                                        <p className="card-text">{restaurant.address} {restaurant.city} {restaurant.state} </p>
                                        <p className="card-text">Call : {restaurant.phone}</p>
                                        <p className="card-text"><a href={restaurant.reserve_url}>Reserve </a>
                                            <button ref={"click"} key={index} className={"btn btn-primary btn-sm"}
                                                    data-key={restaurant.id} onClick={this.handleButton.bind(this,restaurant.id)}>More info
                                            </button>
                                        </p>
                                    </div>
                                </div>
                            </div>
                        )}
                    </div>

                </div>
            )
        }else {
            return(
                <div className={"m-t-5"}>
                <MoreinfoCustomer restid={this.state.restId}/>
                </div>
            )
        }
    }


}