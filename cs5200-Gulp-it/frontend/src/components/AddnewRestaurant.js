import React from 'react';

export default class AddnewRestaurant extends React.Component{
    constructor(props){
        super(props);
        this.state={
            name:'',
            description:'',
            image_link:'',
            cost_for_two:'',
            type:'',
            id:localStorage.getItem('userid')

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
save(e){
  e.preventDefault();
  var url='http://localhost:8080/api/owner/'+this.state.id +'/restaurant'
  console.log("Success from posting rest page!")
  fetch(url, {
      method: 'POST',
      headers: {
          'Accept': 'application/json',
          'Content-Type': 'application/json',
      },
      body: JSON.stringify({
            name: this.state.name,
            description: this.state.description,
            image_link: this.state.image_link,
            cost_for_two: this.state.cost_for_two,
            restaurant_type: this.state.type
      })
  }).then(console.log("saved to the db"));
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
