import React from 'react';
import axios from 'axios';

export default class SearchHome extends React.Component{
    constructor(props) {
        super(props);
        this.state = {
            username:""
        };
        this.submitHandler = this.submitHandler.bind(this)
    }

    submitHandler(){
        axios.get('http://localhost:8080/api/test').then(response=>this.setState({username:response.data}));
        console.log(this.state.username);

    }

    render(){
        return(
            <div className={'container text-center p-5'}>
                <h1 id={'homeHeader'}>
                    GULP IT
                </h1>
                <h2 id={'homeHeader2'}>
                    Where your hunger gets the satisfaction
                </h2>
                <div className={"container col-lg-9 text-center p-5"}>
                    <div className={"text-center "}>
                        <div className={"input-group"}>
                            <input type="text" className="form-control" placeholder="Search Begins Here"/>
                            <span className="text-center input-group-btn">
                               <button className="text-center btn btn-primary" type="button" onClick={this.submitHandler}>Search</button>
                             </span>

                        </div>
                    </div>
                </div>
                <div id={"radioSelector"}>
                    <div className="form-check form-check-inline">
                        <input className="form-check-input" type="radio" name="inlineRadioOptions" id="inlineRadio1" value="option1"/>
                        <label className="form-check-label" htmlFor="inlineRadio1">Name</label>
                    </div>
                    <div className="form-check form-check-inline">
                        <input className="form-check-input" type="radio" name="inlineRadioOptions" id="inlineRadio2" value="option2"/>
                        <label className="form-check-label" htmlFor="inlineRadio2">City</label>
                    </div>
                    <div className="form-check form-check-inline">
                        <input className="form-check-input" type="radio" name="inlineRadioOptions" id="inlineRadio3" value="option3" />
                        <label className="form-check-label" htmlFor="inlineRadio3">Zip Code</label>
                    </div>
                    <div className="form-check form-check-inline">
                        <input className="form-check-input" type="radio" name="inlineRadioOptions" id="inlineRadio3" value="option3" />
                        <label className="form-check-label" htmlFor="inlineRadio3">Country</label>
                    </div>
                </div>
            </div>
        );
    }
}
