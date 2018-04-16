import React from 'react';
import axios from 'axios';
import Navbar from "./Navbar";

export default class MoreinfoCustomer extends React.Component{
    constructor(props){
        super(props);
        this.state={
            feedbacks:[]
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
            }).then(console.log(this))

        const string2 = 'http://localhost:8080/api/feedback/1';
        axios.get(string2)
            .then(result => {
                console.log(result)
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

    handlePostComment(e){
        var self = this;
        var comment=self.state.newComment;

        console.log(comment);
        // console.log(fav)

        axios.post("http://localhost:8080/api/feedback/1/8",{
            comment:comment

        }).then(
            this.render()
        );
        // self.setState(
        //     {
        //         fav:false
        //     }
        // );

    }
update(e){
    console.log(this);
        this.setState(
            {
                newComment:this.refs.commentBody.value
            }
        )
}
favClick(e){
    var self = this;

        e.preventDefault();
    axios.post("http://localhost:8080/api/feedback/1/8",{
        fav:true
    });
        this.setState({
            fav:true
        })
}

    render(){
        console.log(this);
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
                <button className={"btn btn-primary"} onClick={this.favClick.bind(this)}>Favourite</button>
            </div>
                <div className="col-8">
                    <h1>
                        Feedback
                    </h1>
                        {this.state.feedbacks.map((feedback)=>
                            <div className={"comment m-2 text-left p-2"}>
                                    <h4>{feedback.comment}</h4>
                                    <p>Favourite: {feedback.favourite==0?"False":"true"}</p>

                            </div>

                        )
                        }
                    <div className="form-group m-2">
                        <form className="form-inline">
                        <input onChange={this.update.bind(this)} ref="commentBody" type="text" className="form-control col-11" id="comment" placeholder="Add New Comment"/>
                        <button className={"btn btn-primary"} onClick={this.handlePostComment.bind(this)}>Submit</button>
                        </form>
                    </div>

                </div>

            </div>)
   }
}