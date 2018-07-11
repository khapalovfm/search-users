import React, {Component} from 'react';
import axios from "axios/index";
import Input from 'arui-feather/textarea';
import Button from 'arui-feather/button';

class AddUserComponent extends Component {
    constructor() {
        super()
        this.handleClick = this.handleClick.bind(this)
    }

    handleClick() {
        let url = 'http://localhost:8080/search-1.0-SNAPSHOT/rest/user/saveUser'
        axios.post(url, {
                organization_name: document.getElementById('organization_name').value,
                phone: document.getElementById('phone').value
            }
        )
            .then(response => {
                alert('Saved')
            })
    }

    render() {
        return (<form>
            <Input id={'organization_name'} name={'organization_name'} label={'Название организации'}/>
            <Input id={'phone'} name={'phone'} label={'Нoмер телефона'}/>
            <Button size={'l'} onClick={this.handleClick}/>

        </form>)
    }
}

export default AddUserComponent;