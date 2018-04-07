import React from 'react';

export default class SearchHome extends React.Component{
    constructor(props) {
        super(props);

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
                            <input type="text" className="form-control" placeholder="Restaurant Name"/>
                            <span className="text-center input-group-btn">
                               <button className="text-center btn btn-primary" type="button">Search</button>
                             </span>
                        </div>
                    </div>
                </div>
            </div>
        );
    }
}
