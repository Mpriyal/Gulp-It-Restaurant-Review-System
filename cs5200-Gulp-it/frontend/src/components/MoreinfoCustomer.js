import React from 'react';
import axios from 'axios';
import Navbar from "./Navbar";

export default class MoreinfoCustomer extends React.Component{
    constructor(props){
        super(props);
        this.state={
            feedbacks:[],
            userid:localStorage.getItem("userid"),
            btnclass:"btn btn-primary",
            flag:false
        }

    }

    componentWillMount() {
        let restId = this.props.restid;
        let testurl="http://localhost:8080/api/restaurant/2";
        let url = "http://localhost:8080/api/restaurant/"+restId;
        let url2="http://opentable.herokuapp.com/api/restaurants/"+restId;
        axios.get(testurl)
            .then(res => {
                console.log(res);
                if(res.data.length!==0){
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
                }
            })

        axios.get(url2)
            .then(res => {
                console.log("api data")
                console.log(res);
                this.setState({
                    id: res.data.id,
                    name: res.data.name,
                    description: res.data.description,
                    image_link: res.data.image_url,
                    cost_for_two: res.data.price,
                    restaurant_owner: "Not Available",
                    restaurant_type: "Not Available",
                    newComment:'',
                    fav:false
                })
            })

        let test='http://localhost:8080/api/feedback/1'
        const string2 = 'http://localhost:8080/api/feedback/'+this.props.restid;
        axios.get(test)
            .then(result => {
                console.log(result)
                this.setState({
                    feedbacks: result.data
                })
            }).then(console.log(this));
        console.log("after mounting");
        console.log(this)
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
        e.preventDefault();
        if(this.state.userid==null){
            alert("Please sign in first")
            return;
        }
        else{
            var self = this;
            var comment=self.state.newComment;
            console.log(comment);
            let testurl="http://localhost:8080/api/feedback/1/8";
            let url="http://localhost:8080/api/feedback/";
            // axios.post(testurl+this.state.userid+"/"+this.props.restid,{
        axios.post(testurl,{
                comment:comment
            }).then(this.render());

        }}
    update(e){
        console.log(this);
        this.setState(
            {
                newComment:this.refs.commentBody.value
            }
        )
    }
    favClick(e){
        // if(this.state.userid==null){
        //     alert("Please sign in first")
        //     return;
        // }
        let testurl="http://localhost:8080/api/feedback/1/8";
        let url = "http://localhost:8080/api/feedback/"+this.state.userid+"/"+this.props.restid;
        console.log(testurl);
        e.preventDefault();
        axios.post(testurl,{
            favourite:true
        });
        this.setState({
            btnclass:"btn btn-success",
            fav:true
        })
    }

    render(){
        console.log("before main")
        console.log(this);
        return(
            <div className={"row"}>

                <div className="profile col-3">
                    <p className={"head"}>{this.state.name}</p>
                    <img src={this.state.image_link} alt={"restaurant pic not available"}/>
                    <br/>
                    <br/>
                    <p>Id: {this.state.id}</p>
                    <p>Description: {this.state.description}</p>
                    <p>Cost For Two: {this.state.cost_for_two}</p>
                    <p>Restaurant Owner: {this.state.restaurant_owner}</p>
                    <p>Restaurant Type: {this.restaurantType()}</p>
                    <button className={this.state.btnclass} onClick={this.favClick.bind(this)}>Favourite</button>
                </div>
                <div className="col-8">
                    <p className={"head"}>
                        Feedback
                    </p>
                    {this.state.feedbacks.map((feedback,i)=>
                        <div key={i} className={"comment m-2 text-left p-2"}>
                            <h4>{feedback.comment}</h4>
                            <p>Favourite: {feedback.favourite==0?"False":"true"}</p>
                        </div>
                    )
                    }
                    <div className="form-group m-1">
                        <form className="form-inline">
                            <input onChange={this.update.bind(this)} ref="commentBody" type="text" className="form-control col-10" id="comment" placeholder="Add New Comment"/>
                            <button className={"btn btn-primary"} onClick={this.handlePostComment.bind(this)}>Submit</button>
                        </form>
                    </div>

                </div>

            </div>)
    }
}
