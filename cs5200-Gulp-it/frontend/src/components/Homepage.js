import React from 'react'
import Navbar from "./Navbar";
import SearchHome from "./SearchHome";

export default class Homepage extends React.Component{
    constructor(props){
        super(props);
    }

    render(){
        return(
            <div>
            <Navbar/>
            <SearchHome/>
            </div>
        )
    }
}