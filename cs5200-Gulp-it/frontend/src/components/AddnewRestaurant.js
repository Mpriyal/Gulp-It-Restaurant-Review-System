import React from 'react';

export default class AddnewRestaurant extends React.Component{
    constructor(props){
        super(props);
        this.state={
            name:'',
            description:'',
            image_link:'',
            cost_for_two:'',
            type:''
        }
    }

    update(){
        console.log(this);
        this.setState(
            {
                name: this.refs.name.value,
                description: this.refs.des.value,
                image_link: this.refs.imglink.value,
                cost_for_two: this.refs.cft.value,
                type: this.refs.type.value,
            });
    }
save(){
        // post request
    // send a post reqest
}
    render(){
        return(
                <div className={'profile col-3'}>
                    <p className={"head"}>Add New Restaurant</p>
                    <form>
                        <div className="form-group">
                            <input
                                ref="name"
                                type="text"
                                className="form-control"
                                id="id"
                                placeholder="Name"
                                onChange={this.update.bind(this)}
                            />
                        </div>
                        <div className="form-group">
                            <input
                                ref="des"
                                type="text"
                                className="form-control"
                                id="LastName"
                                placeholder="Description"
                                onChange={this.update.bind(this)}
                            />
                        </div>

                        <div className={'form-group'}>
                            <input
                                ref="imglink"
                                type="text"
                                className="form-control"
                                id="Id"
                                placeholder={'Image Link'}
                                onChange={this.update.bind(this)}
                            />
                        </div>

                        <div className="form-group">
                            <input
                                ref="cft"
                                type="text"
                                className="form-control"
                                id="cost for two"
                                placeholder="cost for two"
                                onChange={this.update.bind(this)}
                            />
                        </div>


                        <div className="form-group">
                            <input
                                ref="type"
                                type="text"
                                className="form-control"
                                id="type"
                                placeholder="type"
                                onChange={this.update.bind(this)}
                            />
                        </div>

                        <button
                            onClick={this.save.bind(this)}
                            type="submit"
                            className="btn btn-primary"
                        >Save
                        </button>
                    </form>
                </div>

        )
    }
}
