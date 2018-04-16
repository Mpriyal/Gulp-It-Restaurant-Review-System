import React from 'react';
import axios from 'axios';


export default class MoreinfoOwner extends React.Component{
    constructor(props){
        super(props);
        this.state={
            feedbacks:[],
            update:false,
            id: '',
            name:'',
            description:'' ,
            image_link:'',
            cost_for_two:'',
            restaurant_type:'',
        }
    }

    componentDidMount() {
        const string = 'http://localhost:8080/api/restaurant/2';
        axios.get(string)
            .then(res => {
                console.log(res);
                this.setState({
                    id: res.data.id,
                    name: res.data.name,
                    description: res.data.description,
                    image_link: res.data.image_link,
                    cost_for_two: res.data.cost_for_two,
                    restaurant_owner: res.data.restaurant_owner,
                    restaurant_type: res.data.restaurant_type,
                    newComment:'',
                    fav:false
                })
            }).then(console.log(this));

        const string2 = 'http://localhost:8080/api/feedback/1';
        axios.get(string2)
            .then(result => {
                console.log(result);
                this.setState({
                    feedbacks: result.data
                })
            }).then(console.log(this));
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

    delete(){
        // send a delete request to the api
        // axios.delete(url, { data: { foo: "bar" } });
    }

   update(){
        this.setState({
            update:true
        })
   }

    save() {
        this.setState(
            {
                name: this.refs.name.value,
                description:this.refs.des.value,
                image_link: this.refs.imglink.value,
                cost_for_two: this.refs.cft.value,
                restaurant_type: this.refs.type.value,
                update:false

            });


        //PUT THE PUR REQUEST TO UPDATE THE RESTAURANT
        // axios.put(url, { foo: "bar" });


    }

    renderUpdate(){
        return (
            <div className={'profile col-3'}>
                <h1>Update Restaurant</h1>
                <form>
                    <div className="form-group">
                        <input
                            ref="name"
                            type="text"
                            className="form-control"
                            id="id"
                            placeholder="Name"
                            defaultValue={this.state.name}
                        />
                    </div>
                    <div className="form-group">
                        <input
                            ref="des"
                            type="text"
                            className="form-control"
                            id="LastName"
                            placeholder="Description"
                            defaultValue={this.state.description}
                        />
                    </div>

                    <div className={'form-group'}>
                        <input
                            ref="imglink"
                            type="text"
                            className="form-control"
                            id="Id"
                            placeholder={'Image Link'}
                            defaultValue={this.state.image_link}
                        />
                    </div>

                    <div className="form-group">
                        <input
                            ref="cft"
                            type="text"
                            className="form-control"
                            id="cost for two"
                            placeholder="cost for two"
                            defaultValue={this.state.cost_for_two}
                        />
                    </div>


                    <div className="form-group">
                        <input
                            ref="type"
                            type="text"
                            className="form-control"
                            id="type"
                            placeholder="type"
                            defaultValue={this.state.restaurant_type}
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
        )
    }


    renderNormal(){

        return(
            <div className={"row"}>

                <div className="profile col-3">
                    <h1>{this.state.name}</h1>
                    <img src={this.state.image_link} alt={"restaurant pic not available"}/>
                    <br/>
                    <br/>
                    <p>Id: {this.state.id}</p>
                    <p>Description: {this.state.description}</p>
                    <p>Cost For Two: {this.state.cost_for_two}</p>
                    <p>Restaurant Owner: {this.state.restaurant_owner}</p>
                    <p>Restaurant Type: {this.restaurantType()}</p>
                    <button className={"btn btn-primary m-1"} onClick={this.update.bind(this)}>Update</button>
                    <button className={"btn btn-danger m-1"} onClick={this.delete.bind(this)}>Delete</button>
                </div>
                <div className="col-4">
                    <p className={"head"}>
                        Comments
                    </p>

                    {/*// use find customer by id to display the name of the customer who has commented*/}
                    {this.state.feedbacks.map((feedback)=>
                        <div className={"comment m-2 text-left p-2"}>
                            <h4>{feedback.comment}</h4>
                        </div>
                    )
                    }


                </div>
                <div className={"col-5"}>
                    <p className={"head"}>
                       No of People favourited your restaurant :<br/>
                        {this.state.feedbacks.length}
                    </p >
                </div>

            </div>)

    }
    render(){
        console.log(this);
        if(this.state.update===false){
            return (
                this.renderNormal()
            )
        }
        else {
            return this.renderUpdate();
        }

    }
}
