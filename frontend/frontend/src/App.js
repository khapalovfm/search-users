import React, {Component} from 'react';
import Textarea from 'arui-feather/textarea';
import Button from 'arui-feather/button';
import axios from 'axios'
import './App.css';
import AddUserComponent from "./AddUserComponent";

class App extends Component {
    constructor() {
        super()
        this.state = {
            username: [],
            phone: [],
            showComponent: false,
            saveComponent: false
        }
        this.handleClick = this.handleClick.bind(this)

    }

    handleClick() {
        let url = 'http://localhost:8080/search-1.0-SNAPSHOT/rest/user/allUsersInOrganization/'
        axios.get(url.concat(document.getElementById('text').value))
            .then(response => {
                let names = [];
                let phones = [];
                response.data.forEach(e => {
                    names.push(e.name);
                    phones.push(e.phone)
                });
                this.setState({username: names, phone: phones, showComponent: true})
            })
    }

    onClick = () => {
        this.setState({showComponent: false, saveComponent: false})
    }


    render() {
        let zip = (a1, a2) => a1.map((x, i) => [x, a2[i]]);
        return (
            <div className="App">
                <Textarea id='text' placeholder='Введите название организации'/>
                <div className="App-button">
                    <Button size='l' onClick={() => {
                        this.handleClick();
                        this.setState({showComponent: true})
                    }}>{'Найти'}</Button>
                    {this.state.showComponent ?
                        <div>
                            <table className={'table-app'}>
                                <tbody>
                                <tr>
                                    <th>{'Имена'}</th>
                                    <th>{'Телефон'}</th>
                                </tr>
                                {zip(this.state.username, this.state.phone).map((u, v) => <tr>
                                    <td>{u[0]}</td>
                                    <td>{u[1]}</td>
                                </tr>)}
                                </tbody>
                            </table>
                            <Button size='l' onClick={this.onClick}>{'Скрыть'}</Button>
                            <Button size='l' className={'add-button'}
                                    onClick={() => this.setState({saveComponent: true})}>{'Добавить'}</Button>
                            {
                                this.state.saveComponent ?
                                    <AddUserComponent/>
                                    : null
                            }
                        </div>
                        : null}
                </div>
            </div>
        );
    }
}

export default App;
