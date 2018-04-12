import React from 'react';
import axios from 'axios';

export default class Moreinfo extends React.Component{
    constructor(props){
        super(props);
        this.state={
            restaurant:{

            }
        }
    }

    componentDidMount(){
       const string = 'http://opentable.herokuapp.com/api/restaurants/'+this.props.data;
        axios.get(string)
            .then(res=>{
                console.log(res)
                const restaurants = res.data.restaurants;
                this.setState({restaurants})
            })
    }

    render(){
        return(
            <p>
                {/*{this.props.data}*/}
            </p>
        )
    }
}