import React from 'react';
import SearchHome from "./SearchHome";
import axios from 'axios'
import Register from "./Register";

export default class Navbar extends React.Component {
    constructor(props) {
        super(props);
        this.state = {

            username: '',
            userID: '',
            password: '',
            loggedIn: false,
            adminlogin: false,
            role: '',
            status: '',
            register: false
        };
        this.OnSignin = this.OnSignin.bind(this);
        this.Register = this.Register.bind(this);
        this.OnLogout = this.OnLogout.bind(this);
    }

    OnLogout(e){
        e.preventDefault();
        this.setState(
            {
                loggedIn:false,
                username: '',
                userID: '',
                password: '',
            }

        )
        console.log(this)
    }

    update(e) {
        console.log(this);
        this.setState({
            username: this.refs.Username.value,
            password: this.refs.Password.value

        })

    }

    OnSignin(e){
        var self = this;
        e.preventDefault();
        axios.get('http://localhost:8080/api/customer', {
            params: {
                username: this.state.username,
                password: this.state.password
            }
        })
            .then(function(response){
            self.setState({
                userID:response.data.id,
                loggedIn: true});
                console.log(self)
            })
            .catch(function (error) {
                console.log(error);
            });
        console.log(this);
    }

    Register() {

        this.setState({
            register: true
        })
    }

    render() {
        if(this.state.loggedIn===false){
        if (this.state.register === false) {
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
                                    <a className="nav-link" href="#">Profile</a>
                                </li>
                                <li className="nav-item">
                                    <a className="nav-link disabled" href="#">Disabled</a>
                                </li>
                            </ul>
                            <form>
                                <div className="row">
                                    <div className="col">
                                        <input type="text" className="form-control" placeholder="User name"
                                               ref="Username"  onChange={this.update.bind(this)} />
                                    </div>
                                    <div className="col">
                                        <input type="password" className="form-control" placeholder="Password"
                                               ref="Password"  onChange={this.update.bind(this)} />
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

                </div>
            )
        }
        else {
            return (
                <Register/>
            )
        }
        }else{return(
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
                                <a className="nav-link" href="#">Profile</a>
                            </li>
                            <li className="nav-item">
                                <a className="nav-link disabled" href="#">Disabled</a>
                            </li>
                        </ul>
                        <form>
                            <div className="row">

                                <div>
                                    <button type="submit" className="btn btn-primary btn-sm"
                                            onClick={this.OnLogout}>Logout
                                    </button>
                                </div>

                            </div>
                        </form>
                    </div>
                </nav>

            </div>
            )
        }
    }
}