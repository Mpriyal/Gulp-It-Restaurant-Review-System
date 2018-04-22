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
            flag:false,
            custId:''
        }

    }

    componentWillMount() {
        let restId = this.props.restid;
        let testurl="http://localhost:8080/api/restaurant/2";
        let url = "http://localhost:8080/api/restaurant/"+restId;
        let url2="http://opentable.herokuapp.com/api/restaurants/"+restId;
        axios.get(url)
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
        var url3='http://localhost:8080/api/user/'+this.state.userid
          var self=this
        axios.get(url3).then(

          function(res){
            self.setState({
                custId:res.data
            })
            console.log(res);
          }
        )
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
        axios.get(string2)
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

    handleDeletComment(fid,index){
      console.log(index);
      console.log(fid);
      var url="http://localhost:8080/api/customer/"+this.state.custId+"/feedback/"+fid
       axios.delete(url).then(
        console.log("deleted feedback")
         )
         var array = this.state.feedbacks;
         array.splice(index, 1);
         this.setState({
           feedbacks:array
         })



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
            let url="http://localhost:8080/api/feedback/"+this.props.restid+"/"+this.state.custId;
        axios.post(url,{
                comment:comment
            })

}
}
handleRefresh(e){

            e.preventDefault();
            let test='http://localhost:8080/api/feedback/1'
            const string2 = 'http://localhost:8080/api/feedback/'+this.props.restid;
            axios.get(string2)
                .then(result => {
                    console.log(result)
                    this.setState({
                        feedbacks: result.data
                    })
                })

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
        if(this.state.userid==null){
            alert("Please sign in first")
            return;
        }
        let testurl="http://localhost:8080/api/feedback/1/8";
        let url = "http://localhost:8080/api/feedback/"+this.props.restid+"/"+this.state.userid;
        console.log(url);
        e.preventDefault();
        axios.post(url,{
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
                    {this.state.feedbacks.map((feedback,index)=>
                        <div key={index} className={"comment m-2 text-left p-2"}>
                            <h4>{feedback.comment}</h4>
                            <p>Favourite: {feedback.favourite==0?"False":"true"}

                            <button onClick={this.handleDeletComment.bind(this,feedback.id,index)} className={feedback.customer==this.state.custId?
                              "btn btn-danger btn-sm float-right":"btn btn-success float-right hidden"} >
                               Delete</button>
                            </p>

                        </div>
                    )
                    }
                    <div className="form-group m-1">
                        <form className="form-inline">
                            <input onChange={this.update.bind(this)}
                            ref="commentBody" type="text" className="form-control col-9"
                            id="comment" placeholder="Add New Comment"/>
                            <p>
                            <button className={"btn btn-primary"} onClick={this.handlePostComment.bind(this)}>Submit</button>
                            <button className={"btn btn-primary"} onClick={this.handleRefresh.bind(this)}>Refresh</button>
                            </p>
                        </form>
                    </div>

                </div>

            </div>)
    }
}
