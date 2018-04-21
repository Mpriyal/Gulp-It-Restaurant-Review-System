import React from 'react'

export default class NavLeft extends React.component {
  constructor() {

render(){
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
                    <Link to='/'>Home__</Link>

                    </li>
                    <li className="nav-item">
                    <Link to='/profile'>Profile </Link>

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
  }
}
