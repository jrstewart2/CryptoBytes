import Nav from './Nav.jsx'
import { useState } from 'react';
import axios from 'axios';
import { Button } from 'react-bootstrap';
import { Link, useNavigate } from 'react-router-dom';

const AddCrypto = () => {
    const [symbol, setSymbol] = useState("");
    const [name, setName] = useState("");
    const [coins, setCoins] = useState("");
    const navigate = useNavigate();

    const handleSubmit = (e) => {
        e.preventDefault();
    }

    const handleForm1 = (e) => {
        setSymbol(e.target.value);
    }

    const handleForm2 = (e) => {
        setName(e.target.value);
    }

    const handleForm3 = (e) => {
        console.log(e.target.value)
        setCoins(e.target.value);
    }

    const createObject = () => {

        let newCryptoObject = {
            symbol: symbol,
            name: name,
            coins: coins
        }

        const token = localStorage.getItem('token');
        const authUser = localStorage.getItem('username')
        const postURL = `http://localhost:8080/api/portfolio/${authUser}`;

        axios.post(postURL, newCryptoObject, {
            headers: {Authorization: `Bearer ${token}`},})
        navigate("/portfolio");
    }

    return (
        <>
            <Nav />
            <form onSubmit={handleSubmit}>
                <input placeholder="Crypto symbol:" type="text" value={symbol} onChange={handleForm1} />
                <input placeholder="Crypto name:" type="text" value={name} onChange={handleForm2} />
                <input placeholder="Amount:" type="text" value={coins} onChange={handleForm3} />
                <Link to="/portfolio"><Button variant="info" type="button" onClick={createObject}>Add</Button></Link>
            </form>
        </>
    )
}
export default AddCrypto;