import React from 'react';
import SearchHome from "./SearchHome";
import axios from 'axios'
import Register from "./Register";

export default class Navbar extends React.Component{
    constructor(props) {
        super(props);
        this.state = {

            username:'' ,
            userID: '',
            password: '',
            loggedIn:false,
            adminlogin:false,
            role: '',
            status: '',
            register: false};
        this.OnSignin = this.OnSignin.bind(this);
        this.Register = this.Register.bind(this);
    }


    OnSignin(){

        fetch('/login', {
            method: 'POST',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({
                userId: this.state.userID,
                password: this.state.password,
            })
        }).then(function(response) {
            return response.json();
        }).then(j =>
            this.setState({
                username: Object.values(j)[1].name,
                adminlogin: Object.values(j)[1].userRole === 'admin' ? true : false,
                role: Object.values(j)[1].userRole,
                isLoggedIn: true})
        ).catch(function() {
            alert("Login Error! Please try again.")
        });
    }

    Register(){
        this.setState({
            register:true
        })
    }
    render() {
        if(this.state.register===false) {
            return (
                <div className={"navbarComponents"}>
                    <nav className="navbar navbar-expand-lg navbar-light bg-light">
                        <a className="navbar-brand" href="#">Gulp-It</a>
                        <button className="navbar-toggler" type="button" data-toggle="collapse"
                                data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent"
                                aria-expanded="false" aria-label="Toggle navigation">
                            <span className="navbar-toggler-icon"></span>
                        </button>

                        <div className="collapse navbar-collapse" id="navbarSupportedContent">
                            <ul className="navbar-nav mr-auto">
                                <li className="nav-item active">
                                    <a className="nav-link" href="#">Home <span className="sr-only">(current)</span></a>
                                </li>
                                <li className="nav-item">
                                    <a className="nav-link" href="#">Link</a>
                                </li>
                                <li className="nav-item">
                                    <a className="nav-link disabled" href="#">Disabled</a>
                                </li>
                            </ul>
                            <form>
                                <div className="row">
                                    <div className="col">
                                        <input type="text" className="form-control" placeholder="User name"/>
                                    </div>
                                    <div className="col">
                                        <input type="password" className="form-control" placeholder="Password"/>
                                    </div>
                                    <div>
                                        <button type="submit" className="btn btn-primary btn-sm"
                                                onClick={this.OnSignin}>Sign In
                                        </button>
                                    </div>
                                    <div className="col">
                                        <button type="register" className="btn btn-success btn-sm"
                                                onClick={this.Register}>Register
                                        </button>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </nav>
                    <SearchHome/>
                </div>
            )
        }
        else{
            return(
                <Register/>
            )
        }
    }
}