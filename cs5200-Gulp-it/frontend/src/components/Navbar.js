import React from 'react';

export default class Navbar extends React.Component{
    constructor(props) {
        super(props);

    }

    render() {
        return(
            <div className={"navbarComponents"}>
                <nav className="navbar navbar-expand-lg navbar-light bg-light">
                    <a className="navbar-brand" href="#">Gulp-It</a>
                    <button className="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
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
                                    <input type="text" className="form-control" placeholder="Password"/>
                                </div>
                                <div>
                                    <button type="submit" className="btn btn-primary btn-sm">Sign In</button>
                                </div>
                                <div className="col">
                                    <button type="register" className="btn btn-success btn-sm">Register</button>
                                </div>
                            </div>
                        </form>
                    </div>
                </nav>
            </div>
        )
    }
}