import React from 'react';
import axios from 'axios'
import Register from "./Register";
import { Link } from 'react-router-dom'


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
            register: false,
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
        );
        localStorage.removeItem("userid")
        console.log(this)
        this.forceUpdate()
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
        axios.get('http://localhost:8080/api/user', {
            params: {
                username: this.state.username,
                password: this.state.password
            }
        }).then(function(response){
            console.log(response);
            if(response.data.length===0){
                alert("Sorry Wrong credentials.!! Try Again");
            }
            else if(response.data.password===self.state.password) {
                self.setState({
                    userID: response.data.id,
                    loggedIn: true,
                    role:response.data.type
                });
                localStorage.setItem('userid',response.data.id);
                localStorage.setItem('role',response.data.type);
                console.log(self)
            }})
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
        if(!localStorage.getItem('id')&!this.state.loggedIn){
        if (!this.state.register) {
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
                            <li className="nav-item">
                            <button className="btn btn-primary btn-sm">
                                <Link style={{display: 'block', height: '100%',color:'white'}} to='/'>Home</Link>
                                </button>
                            </li>  <li className="nav-item">
                                  <a className="nav-link disabled" href="#">Profile</a>
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
                                        <button className="btn btn-success btn-sm">
                                         <Link style={{display: 'block', height: '100%',color:'white'}} to='/registeration'>Register</Link>
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
        }
        else
        {if(this.state.role=='customer'){
          return(
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
                      <button className="btn btn-primary btn-sm">
                      <Link style={{display: 'block', height: '100%',color:'white'}} to='/'>Home</Link>
                      </button>
                        </li>
                        <li className="nav-item">
                  <button className="btn btn-primary btn-sm">
                  <Link style={{display: 'block', height: '100%',color:'white'}} to='/profile'>Profile </Link>
                  </button>
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
          )}
          else{
            return(
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
                        <button className="btn btn-primary btn-sm"><Link style={{display: 'block', height: '100%',color:'white'}} to='/'>Home</Link></button>
                          </li>
                          <li className="nav-item">
                          <button className="btn btn-primary btn-sm">
                          <Link style={{display: 'block', height: '100%',color:'white'}} to='/owner'>Profile </Link>
                          </button>
                          </li>

                          </ul>
                          <form>
                              <div className="row">

                                  <div>
                                      <button type="submit" className="btn btn-primary btn-sm"
                                              onClick={this.OnLogout}>
                                              <Link style={{display: 'block', height: '100%',color:'white'}} to='/'>Logout</Link>

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
}
