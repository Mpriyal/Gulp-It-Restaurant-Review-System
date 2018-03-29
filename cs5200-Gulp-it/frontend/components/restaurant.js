import React from 'react';
import ReactDOM from 'react-dom';
import axios from 'axios';

export default class Restaurant from React.Component{
    constructor(props) {
        super(props);
        this.state = {
            restaurants: []
        };
    }

    componentDidMount() {
        axios.get(`http://www.reddit.com/r/${this.props.subreddit}.json`)
            .then(res => {
                const posts = res.data.data.children.map(obj => obj.data);
                this.setState({ posts });
            });
    }

    render(){
        return(
            <div className={'container'}>

            </div>
        );
    }
}
