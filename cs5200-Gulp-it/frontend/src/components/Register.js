import React from 'react';

export default class Register extends React.Component{

    constructor(props){
        super(props);
        this.state={
            firstName: null,
            lastName: null,
            username: null,
            email:null,
            password:null,
            dateOfBirth: null,
            role:null
        }
    }
    update(){
        this.setState({
            firstName: this.refs.FirstName.value,
            univID: this.refs.LastName.value,
            name: this.refs.name.value,
            password: this.refs.password.value,
            email: this.refs.email.value
        })
    }

    render(){
        return(
            <div className={"container-fluid registerClass"}>
                <div>
                    <h3 className={'text-center'}>Registeration</h3>
                    <div className={'container text-center'} >

                        <form className={'m-5'}>
                            <div className={'form-group'}>
                                <input
                                    ref="newId"
                                    type="text"
                                    className="form-control"
                                    id="Id"
                                    placeholder={'Id'}
                                    onChange={this.update.bind(this)}
                                />
                            </div>
                            <div className="form-group">
                                <input
                                    ref="FirstName"
                                    type="text"
                                    className="form-control"
                                    id="FirstName"
                                    placeholder="First Name"
                                    onChange={this.update.bind(this)}
                                />
                            </div>
                            <div className="form-group">
                                <input
                                    ref="LastName"
                                    type="text"
                                    className="form-control"
                                    id="LastName"
                                    placeholder="Last Name"
                                    onChange={this.update.bind(this)}
                                />
                            </div>
                            <div className="form-group">
                                <input
                                    ref="Username"
                                    type="text"
                                    className="form-control"
                                    id="Username"
                                    placeholder="Username"
                                    onChange={this.update.bind(this)}
                                />
                            </div>
                            <div className="form-group">
                                <input
                                    ref="Email"
                                    type="email"
                                    className="form-control"
                                    id="Email"
                                    placeholder="Email"
                                    onChange={this.update.bind(this)}
                                />
                            </div>
                            <div className="form-group">
                                <input
                                    ref="Password"
                                    type="password"
                                    className="form-control"
                                    id="Password"
                                    placeholder="Password"
                                    onChange={this.update.bind(this)}
                                />
                            </div>
                            <div className="form-group">
                                <input
                                    ref="dateOfBirth"
                                    type="password"
                                    className="form-control"
                                    id="DateOfBith"
                                    placeholder="Date Of Birth"
                                    onChange={this.update.bind(this)}
                                />
                            </div>

                            <div className="form-check form-check-inline">
                                <input className="form-check-input float-left" type="radio" name="inlineRadioOptions" id="inlineRadio1" value="option1"  onChange={this.update.bind(this)}/>
                                <label className="form-check-label" htmlFor={'inlineRadio2'}>Customer</label>
                            </div>
                            <div className="form-check form-check-inline">
                                <input className="form-check-input float-right" type="radio" name="inlineRadioOptions" id="inlineRadio2" value="option2"  onChange={this.update.bind(this)} />
                                <label className="form-check-label" htmlFor={'inlineRadio2'}>Restaurant Owner</label>
                            </div>
                            <div>
                                <button
                                    type="submit"
                                    className="btn btn-primary m-5"
                                >SUBMIT
                                </button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        )
    }
}

