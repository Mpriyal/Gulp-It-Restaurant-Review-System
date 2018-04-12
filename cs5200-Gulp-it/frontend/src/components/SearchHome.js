import React from 'react';
import RestaurantList from './RestaurantList'
import axios from 'axios';

export default class SearchHome extends React.Component{
    constructor(props) {
        super(props);
        this.state = {
            restaurants:[],
            selectedOption:'name'
        };
        this.submitHandler = this.submitHandler.bind(this);
        this.handleOptionChange = this.handleOptionChange.bind(this);

    }

    submitHandler(){

        var textValue = this.refs.searchValue.value;
        console.log(textValue);
        switch(this.state.selectedOption) {
            case 'name':
                axios.get('http://opentable.herokuapp.com/api/restaurants',{
                    params:{
                        name:textValue
                    }
                })
                    .then(res=>{
                        console.log(res)
                        const restaurants = res.data.restaurants;
                        this.setState({restaurants});
                    })
                break;
            case 'city':
                axios.get('http://opentable.herokuapp.com/api/restaurants',{
                    params:{
                        city:textValue
                    }
                })
                    .then(res=>{
                        console.log(res)
                        const restaurants = res.data.restaurants;
                        this.setState({restaurants});
                    })
                break;
            case 'zipcode':
                axios.get('http://opentable.herokuapp.com/api/restaurants',{
                    params:{
                        zip:textValue
                    }
                })
                    .then(res=>{
                        console.log(res)
                        const restaurants = res.data.restaurants;
                        this.setState({restaurants});
                    })
                break;
            case 'country':
                axios.get('http://opentable.herokuapp.com/api/restaurants',{
                    params:{
                        country:textValue
                    }
                })
                    .then(res=>{
                        console.log(res)
                        const restaurants = res.data.restaurants;
                        this.setState({restaurants});
                    })
        }

    }

    handleOptionChange (changeEvent){
        this.setState({
            selectedOption:changeEvent.target.value
        });
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
                            <input type="text" className="form-control" placeholder="Search Begins Here" ref="searchValue"/>
                            <span className="text-center input-group-btn">
                               <button className="text-center btn btn-primary" type="button" onClick={this.submitHandler}>Search</button>
                             </span>

                        </div>
                    </div>
                </div>
                <div id={"radioSelector"}>
                    <div className="form-check form-check-inline">
                        <input className="form-check-input"
                               type="radio" name="inlineRadioOptions"
                               id="inlineRadio1" value="name"
                               checked={this.state.selectedOption==='name'}
                                onChange={this.handleOptionChange}/>
                        <label className="form-check-label"
                               htmlFor="inlineRadio1">Name</label>
                    </div>
                    <div className="form-check form-check-inline">
                        <input className="form-check-input"
                               type="radio" name="inlineRadioOptions"
                               id="inlineRadio2" value="city"
                               checked={this.state.selectedOption ==='city'}
                               onChange={this.handleOptionChange}/>
                        <label className="form-check-label"
                               htmlFor="inlineRadio2">City</label>
                    </div>
                    <div className="form-check form-check-inline">
                        <input className="form-check-input"
                               type="radio" name="inlineRadioOptions"
                               id="inlineRadio3" value="zipcode"
                               checked={this.state.selectedOption==='zipcode'}
                               onChange={this.handleOptionChange}/>
                        <label className="form-check-label"
                               htmlFor="inlineRadio3">Zip Code</label>
                    </div>
                    <div className="form-check form-check-inline">
                        <input className="form-check-input"
                               type="radio" name="inlineRadioOptions"
                               id="inlineRadio3" value="country"
                               checked={this.state.selectedOption==='country'}
                               onChange={this.handleOptionChange}/>
                        <label className="form-check-label"
                               htmlFor="inlineRadio3">Country</label>
                    </div>

                </div>
                <RestaurantList data={this.state.restaurants}/>
            </div>
        );
    }
}

